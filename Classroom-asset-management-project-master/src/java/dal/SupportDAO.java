/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Asset;
import model.MaintenanceRequestView;
import model.Room;

/**
 *
 * @author admin
 */
public class SupportDAO extends DBContext {
    PreparedStatement ps;
    ResultSet rs;
    // Lấy tất cả phòng để đổ vào Dropdown Room
    public List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Room(
                    rs.getInt("room_id"),
                    rs.getString("room_code"),
                    rs.getInt("capacity"),
                    rs.getInt("building_id"),
                    rs.getInt("type_id"),
                    rs.getInt("status_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
public List<Asset> getAllAssets() {
        List<Asset> list = new ArrayList<>();
        String sql = "SELECT * FROM assets";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Chuyển đổi sql.Date sang LocalDate cho Model Asset
                java.sql.Date dbDate = rs.getDate("purchase_date");
                list.add(new Asset(
                    rs.getInt("asset_id"),
                    rs.getString("asset_code"),
                    rs.getString("asset_name"),
                    rs.getInt("category_id"),
                    (Integer) rs.getObject("room_id"), // Sử dụng getObject để xử lý NULL
                    rs.getString("serial_number"),
                    dbDate != null ? dbDate.toLocalDate() : null,
                    rs.getInt("status_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // Lấy tất cả thiết bị (bao gồm cả roomId để JavaScript lọc)
    

    // 1. Lấy lịch sử báo cáo sử dụng Model MaintenanceRequestView
   public List<MaintenanceRequestView> getRequestHistory(int userId) {
        List<MaintenanceRequestView> list = new ArrayList<>();
        // JOIN chính xác từ r.room_id của bảng yêu cầu
        String sql = "SELECT rm.room_code, a.asset_name, r.description, "
                   + "r.created_at, s.status_name "
                   + "FROM maintenance_requests r "
                   + "JOIN assets a ON r.asset_id = a.asset_id "
                   + "JOIN rooms rm ON r.room_id = rm.room_id " 
                   + "JOIN maintenance_status s ON r.status_id = s.status_id "
                   + "WHERE r.reported_by = ? "
                   + "ORDER BY r.created_at DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MaintenanceRequestView view = new MaintenanceRequestView();
                view.setRoomName(rs.getString("room_code"));
                view.setAssetName(rs.getString("asset_name"));
                view.setDescription(rs.getString("description"));
                view.setStatus(rs.getString("status_name"));
                // Lấy ngày giờ định dạng yyyy-MM-dd HH:mm
                view.setCreatedAt(rs.getTimestamp("created_at").toString().substring(0, 16));
                list.add(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // 2. Lưu báo cáo mới (Bỏ Title, Thêm RoomID)
   public boolean insertRequest(int roomId, int assetId, int userId, String description) {
    // Câu lệnh SQL khớp 100% với cột trong DB của bạn
    String sql = "INSERT INTO [maintenance_requests] ([asset_id], [reported_by], [description], [status_id], [created_at], [room_id]) "
               + "VALUES (?, ?, ?, 1, GETDATE(), ?)";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, assetId);
        ps.setInt(2, userId);
        ps.setNString(3, description); // Dùng setNString để hỗ trợ Tiếng Việt
        ps.setInt(4, roomId);
        
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        System.err.println("Lỗi Insert DAO: " + e.getMessage());
        return false;
    }
}
}

