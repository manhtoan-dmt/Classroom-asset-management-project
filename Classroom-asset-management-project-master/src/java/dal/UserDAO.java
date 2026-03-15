/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.StatisticUser;

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
}
