/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Room;
import model.RoomWithName;

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
                String capacity = rs.getString("capacity");
                String buildingId = rs.getString("building_id");
                int typeId = rs.getInt("type_id");
                int statusId = rs.getInt("status_id");
                Room room = new Room(roomId, roomCode, typeId, statusId, typeId, statusId);
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

}
