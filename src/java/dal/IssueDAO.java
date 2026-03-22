/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Issue;
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

    // LIST ALL ISSUES
    public List<Issue> getAllIssues() {

        List<Issue> list = new ArrayList<>();

        String sql = """
        SELECT
                mr.request_id,
                ms.status_name,
                r.room_code,
                a.asset_name,
        	a.asset_id,
                u.full_name,
                s.status_name AS asset_status,
                mr.created_at
                FROM maintenance_requests mr
                JOIN maintenance_status ms ON mr.status_id = ms.status_id
                JOIN assets a ON mr.asset_id = a.asset_id
                JOIN rooms r ON a.room_id = r.room_id
                JOIN users u ON mr.reported_by = u.user_id
                JOIN asset_status s ON a.status_id = s.status_id
                ORDER BY mr.request_id DESC
        """;

        try {

            st = connection.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {

                Issue i = new Issue();

                i.setIssueId(rs.getInt("request_id"));
                i.setIssueStatus(rs.getString("status_name"));
                i.setRoomCode(rs.getString("room_code"));
                i.setAssetName(rs.getString("asset_name"));
                i.setReportedBy(rs.getString("full_name"));
                i.setStatus(rs.getString("asset_status"));
                i.setDate(rs.getString("created_at"));
                i.setAssetId(rs.getInt("asset_id"));
                list.add(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= FILTER =================
    public List<Issue> filter(String date, String status) {

        List<Issue> list = new ArrayList<>();

        String sql = """
            SELECT
            mr.request_id,
            ms.status_name,
            r.room_code,
            a.asset_name,
            u.full_name,
            s.status_name AS asset_status,
            mr.created_at
            FROM maintenance_requests mr
            JOIN maintenance_status ms ON mr.status_id = ms.status_id
            JOIN assets a ON mr.asset_id = a.asset_id
            JOIN rooms r ON a.room_id = r.room_id
            JOIN users u ON mr.reported_by = u.user_id
            JOIN asset_status s ON a.status_id = s.status_id
            WHERE 1=1
        """;

        if (date != null && !date.isEmpty()) {
            sql += " AND CAST(mr.created_at AS DATE) = ?";
        }

        if (status != null && !status.isEmpty()) {
            sql += " AND s.status_name = ?";
        }

        try {

            st = connection.prepareStatement(sql);

            int index = 1;

            if (date != null && !date.isEmpty()) {
                st.setString(index++, date);
            }

            if (status != null && !status.isEmpty()) {
                st.setString(index++, status);
            }

            rs = st.executeQuery();

            while (rs.next()) {

                Issue i = new Issue();

                i.setIssueId(rs.getInt("request_id"));
                i.setIssueStatus(rs.getString("status_name"));
                i.setRoomCode(rs.getString("room_code"));
                i.setAssetName(rs.getString("asset_name"));
                i.setReportedBy(rs.getString("full_name"));
                i.setStatus(rs.getString("asset_status"));
                i.setDate(rs.getString("created_at"));

                list.add(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= DETAIL =================
    public Issue getIssueById(int id) {

        String sql = """
            SELECT
                        mr.request_id,
                        ms.status_name,
                        mr.description,
                        r.room_code,
                        a.asset_name,
            		a.asset_id,
                        u.full_name,
                        s.status_name AS asset_status,
                        mr.created_at
                        FROM maintenance_requests mr
                        JOIN maintenance_status ms ON mr.status_id = ms.status_id
                        JOIN assets a ON mr.asset_id = a.asset_id
                        JOIN rooms r ON a.room_id = r.room_id
                        JOIN users u ON mr.reported_by = u.user_id
                        JOIN asset_status s ON a.status_id = s.status_id
                        WHERE mr.request_id = ?
        """;

        try {

            st = connection.prepareStatement(sql);
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {

                Issue i = new Issue();

                i.setIssueId(rs.getInt("request_id"));
                i.setIssueStatus(rs.getString("status_name"));
                i.setRoomCode(rs.getString("room_code"));
                i.setAssetName(rs.getString("asset_name"));
                i.setAssetId(rs.getInt("asset_id"));
                i.setReportedBy(rs.getString("full_name"));
                i.setStatus(rs.getString("asset_status"));
                i.setDate(rs.getString("created_at"));
                i.setDescription(rs.getString("description"));

                return i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= UPDATE ISSUE =================
    public void updateIssue(int issueId, String issueStatus) {

        String assetStatus = "";

        switch (issueStatus) {

            case "OPEN":
                assetStatus = "Broken";
                break;

            case "IN_PROGRESS":
                assetStatus = "Maintenance";
                break;

            case "COMPLETED":
                assetStatus = "Available";
                break;

            case "CANCELLED":
                assetStatus = "Available";
                break;
        }

        try {

            connection.setAutoCommit(false);

            // UPDATE ISSUE STATUS
            String sql1 = """
                UPDATE maintenance_requests
                SET status_id =
                (SELECT status_id
                 FROM maintenance_status
                 WHERE status_name = ?)
                WHERE request_id = ?
            """;

            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setString(1, issueStatus);
            ps1.setInt(2, issueId);
            ps1.executeUpdate();

            // UPDATE ASSET STATUS
            String sql2 = """
                UPDATE assets
                SET status_id =
                (SELECT status_id
                 FROM asset_status
                 WHERE status_name = ?)
                WHERE asset_id =
                (SELECT asset_id
                 FROM maintenance_requests
                 WHERE request_id = ?)
            """;

            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1, assetStatus);
            ps2.setInt(2, issueId);
            ps2.executeUpdate();

            connection.commit();

        } catch (Exception e) {

            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }
}
