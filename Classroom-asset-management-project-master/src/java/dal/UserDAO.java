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
            st = connection.prepareStatement(sql);

            st.setString(1, username);
            st.setString(2, password);

            rs = st.executeQuery();

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
            return null;
        }

        return null;
    }

    public List<User> getUsers() {

        List<User> lUser = new ArrayList<>();

        try {
            String sql = "SELECT * FROM users";
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {

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
                lUser.add(u);
            }
            return lUser;

        } catch (Exception e) {
            return null;
        }
    }

    public User getUserById(int id) {

        String sql = "SELECT * FROM users WHERE user_id = ?";

        try {
            st = connection.prepareStatement(sql);

            st.setInt(1, id);
            rs = st.executeQuery();

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
            return null;
        }

        return null;
    }

    public void updateUser(User u) {

        String sql = """
                 UPDATE users
                 SET username = ?,
                     password = ?,
                     full_name = ?,
                     email = ?,
                     phone = ?,
                     role_id = ?,
                     status_id = ?
                 WHERE user_id = ?
                 """;
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, u.getUsername());
            st.setString(2, u.getPassword());
            st.setString(3, u.getFullName());
            st.setString(4, u.getEmail());
            st.setString(5, u.getPhone());
            st.setInt(6, u.getRoleId());
            st.setInt(7, u.getStatusId());
            st.setInt(8, u.getUserId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertUser(String username, String password,
            String fullName, String email, String phone,
            int roleId, int statusId) {

        String sql = """
                 INSERT INTO users
                 (username, password, full_name, email, phone, role_id, status_id, created_at)
                 VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE())
                 """;
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, fullName);
            st.setString(4, email);
            st.setString(5, phone);
            st.setInt(6, roleId);
            st.setInt(7, statusId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String isUsernameExist(String username, String email, String phone) {

        String sql = """
                 SELECT username, email, phone
                 FROM users
                 WHERE username = ?
                 OR email = ?
                 OR phone = ?
                 """;

        try {

            st = connection.prepareStatement(sql);

            st.setString(1, username);
            st.setString(2, email);
            st.setString(3, phone);

            rs = st.executeQuery();

            if (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    return "Username already exists!";
                }

                if (email.equals(rs.getString("email"))) {
                    return "Email already exists!";
                }

                if (phone.equals(rs.getString("phone"))) {
                    return "Phone already exists!";
                }
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public List<User> searchUsers(String keyword) {

        List<User> list = new ArrayList<>();

        String sql = """
                 SELECT *
                 FROM users
                 WHERE full_name LIKE ?
                 OR email LIKE ?
                 """;

        try {

            st = connection.prepareStatement(sql);

            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");

            rs = st.executeQuery();

            while (rs.next()) {

                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setRoleId(rs.getInt("role_id"));
                u.setStatusId(rs.getInt("status_id"));
                u.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
