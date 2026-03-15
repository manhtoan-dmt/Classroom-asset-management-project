/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.BookView;
import model.BookingDetail;
import model.BookingView;

public class BookingDAO extends DBContext {

    PreparedStatement st;
    ResultSet rs;

    public List<BookingView> getTodayBookingByRoom(int roomID) {

        List<BookingView> list = new ArrayList<>();

        try {

            String sql = """
                     SELECT b.start_time,
                            b.end_time,
                            u.full_name,
                            b.purpose,
                            s.status_name
                     FROM bookings b
                     JOIN users u ON b.user_id = u.user_id
                     JOIN booking_status s ON b.status_id = s.status_id
                     WHERE b.room_id = ?
                     AND CAST(b.start_time AS DATE) = CAST(GETDATE() AS DATE)
                     """;

            st = connection.prepareStatement(sql);
            st.setInt(1, roomID);

            rs = st.executeQuery();

            while (rs.next()) {

                BookingView b = new BookingView(
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("full_name"),
                        rs.getString("purpose"),
                        rs.getString("status_name")
                );

                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<BookView> getBookingsByDate(String date) {
        List<BookView> list = new ArrayList<>();
        try {
            String sql = """
                     SELECT 
                     b.booking_id,
                     r.room_code,
                     u.full_name,
                     CONVERT(date,b.start_time) as booking_date,
                     FORMAT(b.start_time,'HH:mm') + ' - ' + FORMAT(b.end_time,'HH:mm') as time_slot,
                     bs.status_name
                     FROM bookings b
                     JOIN rooms r ON b.room_id=r.room_id
                     JOIN users u ON b.user_id=u.user_id
                     JOIN booking_status bs ON b.status_id=bs.status_id
                     WHERE CONVERT(date,b.start_time)=?
                     ORDER BY b.start_time
                     """;

            st = connection.prepareStatement(sql);
            st.setString(1, date);

            rs = st.executeQuery();

            while (rs.next()) {

                BookView b = new BookView();

                b.setBookingId(rs.getInt("booking_id"));
                b.setRoomName(rs.getString("room_code"));
                b.setUserName(rs.getString("full_name"));
                b.setDate(rs.getString("booking_date"));
                b.setTimeSlot(rs.getString("time_slot"));
                b.setStatus(rs.getString("status_name"));

                list.add(b);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public void createBooking(int roomId, int userId, Timestamp start, Timestamp end, String purpose) {

        String sql = """
                 INSERT INTO bookings(room_id,user_id,start_time,end_time,status_id,purpose)
                 VALUES(?,?,?,?,1,?)
                 """;

        try {

            st = connection.prepareStatement(sql);

            st.setInt(1, roomId);
            st.setInt(2, userId);
            st.setTimestamp(3, start);
            st.setTimestamp(4, end);
            st.setString(5, purpose);
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BookingDetail getBookingDetailById(int id) {

        String sql = """
        SELECT b.booking_id,
               r.room_code,
               u.full_name,
               u.email,
               bs.status_name,
               b.start_time,
               b.end_time,
               b.created_at,
               b.purpose
        FROM bookings b
        JOIN rooms r ON b.room_id = r.room_id
        JOIN users u ON b.user_id = u.user_id
        JOIN booking_status bs ON b.status_id = bs.status_id
        WHERE b.booking_id = ?
        """;

        try {

            st = connection.prepareStatement(sql);
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {

                BookingDetail b = new BookingDetail();

                b.setBookingId(rs.getInt("booking_id"));
                b.setRoomName(rs.getString("room_code"));
                b.setUserName(rs.getString("full_name"));
                b.setEmail(rs.getString("email"));
                b.setStatus(rs.getString("status_name"));
                b.setPurpose(rs.getString("purpose"));
                b.setCreatedAt(rs.getTimestamp("created_at"));

                return b;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateStatus(int id, int status) {

        String sql = "UPDATE bookings SET status_id=? WHERE booking_id=?";

        try {

            st = connection.prepareStatement(sql);

            st.setInt(1, status);
            st.setInt(2, id);

            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
