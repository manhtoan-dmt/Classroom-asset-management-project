/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.StatisticIssue;

public class IssueDAO extends DBContext {

    PreparedStatement st;
    ResultSet rs;

    public StatisticIssue getStatisticIssue(String date) {
        StatisticIssue sIssue = null;
        try {
            String sql = """
                         SELECT
                         SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) AS [Open],
                         SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) AS InProgress,
                         SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) AS Completed,
                         SUM(CASE WHEN status_id = 4 THEN 1 ELSE 0 END) AS Cancelled
                         FROM maintenance_requests
                         WHERE CAST(created_at AS DATE) = ?;
                         """;
            st = connection.prepareStatement(sql);
            st.setDate(1, java.sql.Date.valueOf(date));
            rs = st.executeQuery();
            while (rs.next()) {
                sIssue = new StatisticIssue(rs.getInt("Open"), rs.getInt("InProgress"), rs.getInt("Completed"), rs.getInt("Cancelled"));
            }
            return sIssue;
        } catch (Exception e) {
            return null;
        }
    }
}
