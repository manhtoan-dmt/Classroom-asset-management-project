/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Building;
import model.Room;
import model.RoomStatus;
import model.RoomType;
import model.RoomView;
import model.RoomWithName;
import model.StatisticRoom;

public class RoomDAO extends DBContext {

    PreparedStatement st;
    ResultSet rs;

    public List<Room> getRoom() {
        List<Room> rooms = new ArrayList<>();
        try {
            String sql = "select *from rooms";
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                int roomId = rs.getInt("room_id");
                String roomCode = rs.getString("room_code");
                int capacity = rs.getInt("capacity");
                int buildingId = rs.getInt("building_id");
                int typeId = rs.getInt("type_id");
                int statusId = rs.getInt("status_id");
                Room room = new Room(roomId, roomCode, capacity, buildingId, typeId, statusId);
                rooms.add(room);
            }
            return rooms;
        } catch (Exception e) {
            return null;
        }
    }

    public List<RoomWithName> getRoomWithName() {
        List<RoomWithName> roomName = new ArrayList<>();
        try {
            String sql = """
                         SELECT 
                         r.room_id,
                         r.room_code,
                         r.capacity,
                         b.building_name,
                         t.type_name,
                         s.status_name
                         FROM rooms r
                         JOIN buildings b ON r.building_id = b.building_id
                         JOIN room_types t ON r.type_id = t.type_id
                         JOIN room_status s ON r.status_id = s.status_id
                         """;
            st = connection.prepareStatement(sql);
            //truyen tham so cho cau lenh
            rs = st.executeQuery(); //read -select
            while (rs.next()) {
                int roomId = rs.getInt("room_id");
                String roomCode = rs.getString("room_code");
                int capacity = rs.getInt("capacity");
                String buildingName = rs.getString("building_name");
                String typeName = rs.getString("type_name");
                String statusName = rs.getString("status_name");

                RoomWithName rm = new RoomWithName(roomId, roomCode, capacity, buildingName, typeName, statusName);
                roomName.add(rm);
            }
            return roomName;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Room> getRoomsWithCode() {

        List<Room> roomList = new ArrayList<>();

        try {
            String sql = "SELECT room_id, room_code FROM rooms";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Room r = new Room();

                r.setRoomId(rs.getInt("room_id"));
                r.setRoomCode(rs.getString("room_code"));

                roomList.add(r);
            }
            return roomList;

        } catch (Exception e) {
            return null;
        }

    }

    public StatisticRoom getStatisticRoom() {
        StatisticRoom sRoom = null;
        try {
            String sql = """
                         SELECT
                         SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) AS Available,
                         SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) AS Occupied,
                         SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) AS Maintenance
                         FROM rooms;
                         """;
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                sRoom = new StatisticRoom(rs.getInt("Available"), rs.getInt("Occupied"), rs.getInt("Maintenance"));
            }
            return sRoom;
        } catch (Exception e) {
            return null;
        }
    }

    public List<RoomView> getAllRooms() {

        List<RoomView> list = new ArrayList<>();

        try {

            String sql = """
                         SELECT r.room_id,
                                r.room_code,
                                t.type_name,
                                b.building_name,
                                r.capacity,
                                s.status_name
                         FROM rooms r
                         JOIN room_types t ON r.type_id = t.type_id
                         JOIN buildings b ON r.building_id = b.building_id
                         JOIN room_status s ON r.status_id = s.status_id
                         ORDER BY r.room_id
                         """;

            st = connection.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {

                RoomView r = new RoomView(
                        rs.getInt("room_id"),
                        rs.getString("room_code"),
                        rs.getString("type_name"),
                        rs.getString("building_name"),
                        rs.getInt("capacity"),
                        rs.getString("status_name")
                );

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<RoomView> searchRoom(String keyword) {

        List<RoomView> list = new ArrayList<>();

        try {

            String sql = """
                     SELECT r.room_id,
                            r.room_code,
                            t.type_name,
                            b.building_name,
                            r.capacity,
                            s.status_name
                     FROM rooms r
                     JOIN room_types t ON r.type_id = t.type_id
                     JOIN buildings b ON r.building_id = b.building_id
                     JOIN room_status s ON r.status_id = s.status_id
                     WHERE r.room_code LIKE ?
                        OR b.building_name LIKE ?
                        OR t.type_name LIKE ?
                     """;

            st = connection.prepareStatement(sql);

            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");
            st.setString(3, "%" + keyword + "%");

            rs = st.executeQuery();

            while (rs.next()) {

                RoomView r = new RoomView(
                        rs.getInt("room_id"),
                        rs.getString("room_code"),
                        rs.getString("type_name"),
                        rs.getString("building_name"),
                        rs.getInt("capacity"),
                        rs.getString("status_name")
                );

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public RoomView getRoomByID(int id) {

        RoomView r = null;

        try {

            String sql = """
                     SELECT r.room_id,
                            r.room_code,
                            t.type_name,
                            b.building_name,
                            r.capacity,
                            s.status_name
                     FROM rooms r
                     JOIN room_types t ON r.type_id = t.type_id
                     JOIN buildings b ON r.building_id = b.building_id
                     JOIN room_status s ON r.status_id = s.status_id
                     WHERE r.room_id = ?
                     """;

            st = connection.prepareStatement(sql);
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {

                r = new RoomView(
                        rs.getInt("room_id"),
                        rs.getString("room_code"),
                        rs.getString("type_name"),
                        rs.getString("building_name"),
                        rs.getInt("capacity"),
                        rs.getString("status_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    public Room getRoomById(int id) {

        Room r = null;

        try {

            String sql = "SELECT * FROM rooms WHERE room_id = ?";

            st = connection.prepareStatement(sql);
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {

                r = new Room(
                        rs.getInt("room_id"),
                        rs.getString("room_code"),
                        rs.getInt("capacity"),
                        rs.getInt("building_id"),
                        rs.getInt("type_id"),
                        rs.getInt("status_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    public void updateRoom(Room r) {

        try {

            String sql = """
            UPDATE rooms
            SET room_code = ?,
                capacity = ?,
                building_id = ?,
                type_id = ?,
                status_id = ?
            WHERE room_id = ?
        """;

            st = connection.prepareStatement(sql);

            st.setString(1, r.getRoomCode());
            st.setInt(2, r.getCapacity());
            st.setInt(3, r.getBuildingId());
            st.setInt(4, r.getTypeId());
            st.setInt(5, r.getStatusId());
            st.setInt(6, r.getRoomId());

            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RoomType> getAllTypes() {

        List<RoomType> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM room_types";

            st = connection.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {

                RoomType t = new RoomType(
                        rs.getInt("type_id"),
                        rs.getString("type_name")
                );

                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<RoomStatus> getAllStatus() {

        List<RoomStatus> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM room_status";

            st = connection.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {

                RoomStatus s = new RoomStatus(
                        rs.getInt("status_id"),
                        rs.getString("status_name")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Building> getAllBuildings() {

        List<Building> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM buildings";

            st = connection.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {

                Building b = new Building(
                        rs.getInt("building_id"),
                        rs.getString("building_name")
                );

                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean isRoomCodeExist(String name, int id) {

        try {

            String sql = "SELECT * FROM rooms WHERE room_code = ? AND room_id <> ?";

            st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, id);

            rs = st.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void addRoom(Room r) {

        try {
            String sql = """
        INSERT INTO rooms(room_code, capacity, building_id, type_id, status_id)
        VALUES (?, ?, ?, ?, ?)
        """;
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, r.getRoomCode());
            st.setInt(2, r.getCapacity());
            st.setInt(3, r.getBuildingId());
            st.setInt(4, r.getTypeId());
            st.setInt(5, r.getStatusId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAllRoomById(int id) {

        List<Room> list = new ArrayList<>();

        String sql = """
                     SELECT room_id, room_code, capacity, building_id, type_id, status_id
                     FROM rooms
                     WHERE status_id = ?
                     ORDER BY room_code
                     """;

        try {


            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            while (rs.next()) {

                Room r = new Room();

                r.setRoomId(rs.getInt("room_id"));
                r.setRoomCode(rs.getString("room_code"));
                r.setCapacity(rs.getInt("capacity"));
                r.setBuildingId(rs.getInt("building_id"));
                r.setTypeId(rs.getInt("type_id"));
                r.setStatusId(rs.getInt("status_id"));

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void deleteRoom(int id) {

    String sql = "DELETE FROM rooms WHERE room_id=?";

    try {

        st = connection.prepareStatement(sql);

        st.setInt(1, id);

        st.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
