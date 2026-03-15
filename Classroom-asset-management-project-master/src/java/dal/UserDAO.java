/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dal;

import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.StatisticUser;
import model.User;
import java.sql.Timestamp;
public class UserDAO extends DBContext {

    PreparedStatement st;
    ResultSet rs;

    public StatisticUser getStatisticUser() {
        StatisticUser sUser = null;
        try {
            String sql = """
                         SELECT
                         SUM(CASE WHEN role_id = 1 THEN 1 ELSE 0 END) AS Admin,
                         SUM(CASE WHEN role_id = 2 THEN 1 ELSE 0 END) AS Staff,
                         SUM(CASE WHEN role_id = 3 THEN 1 ELSE 0 END) AS Teacher,
                         SUM(CASE WHEN role_id = 4 THEN 1 ELSE 0 END) AS Student
                         FROM users
                         """;
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                sUser = new StatisticUser(rs.getInt("Admin"), rs.getInt("Staff"), rs.getInt("Teacher"), rs.getInt("Student"));
            }
            return sUser;
        } catch (Exception e) {
            return null;
        }
    }
    
     public User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND status_id = 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User u = new User();

                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setRoleId(rs.getInt("role_id"));
                u.setStatusId(rs.getInt("status_id"));

                Timestamp ts = rs.getTimestamp("created_at");
                if (ts != null) {
                    u.setCreatedAt(ts.toLocalDateTime());
                }

                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
