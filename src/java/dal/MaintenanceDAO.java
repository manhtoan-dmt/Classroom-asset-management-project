package dal;

import model.MaintenanceRequestView;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class MaintenanceDAO extends DBContext {

    PreparedStatement st;
    ResultSet rs;

    public List<MaintenanceRequestView> getRequests(String date, String status) {
        List<MaintenanceRequestView> list = new ArrayList<>();
        String sql = "SELECT mr.request_id, r.room_code, a.asset_name, u.user_name, " + "mr.description, ms.status_name, mr.created_at, mr.completed_at " + "FROM maintenance_requests mr " + "JOIN assets a ON mr.asset_id = a.asset_id " + "JOIN rooms r ON a.room_id = r.room_id " + "JOIN users u ON mr.reported_by = u.user_id " + "JOIN maintenance_status ms ON mr.status_id = ms.status_id " + "WHERE 1=1 ";
        if (date != null && !date.isEmpty()) {
            sql += " AND CAST(mr.created_at AS DATE) = ?";
        }
        if (status != null && !status.isEmpty()) {
            sql += " AND ms.status_name = ?";
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
                MaintenanceRequestView m = new MaintenanceRequestView(rs.getInt("request_id"), rs.getString("room_code"), rs.getString("asset_name"), rs.getString("user_name"), rs.getString("description"), rs.getString("status_name"), rs.getString("created_at"), rs.getString("completed_at"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public MaintenanceRequestView getRequestById(int id) {
        String sql = "SELECT mr.request_id, r.room_code, a.asset_name, u.user_name, " + "mr.description, ms.status_name, mr.created_at, mr.completed_at " + "FROM maintenance_requests mr " + "JOIN assets a ON mr.asset_id = a.asset_id " + "JOIN rooms r ON a.room_id = r.room_id " + "JOIN users u ON mr.reported_by = u.user_id " + "JOIN maintenance_status ms ON mr.status_id = ms.status_id " + "WHERE mr.request_id = ?";
        try {
            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return new MaintenanceRequestView(rs.getInt("request_id"), rs.getString("room_code"), rs.getString("asset_name"), rs.getString("user_name"), rs.getString("description"), rs.getString("status_name"), rs.getString("created_at"), rs.getString("completed_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
