package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Asset;
import model.AssetTypeStatistic;
import model.AssetCategory;
import model.AssetEdit;
import model.AssetStatus;
import model.AssetStatusHistory;
import model.AssetView;
import model.StatisticAsset;
import model.AssetWithRoom;
import java.time.LocalDate;
import model.AssetDetail;
import model.AssetType;
import model.MaintenanceHistory;

public class AssetDAO extends DBContext {

    PreparedStatement st;
    ResultSet rs;

    public List<AssetTypeStatistic> getAssetStatistics() {

        List<AssetTypeStatistic> list = new ArrayList<>();

        try {
            String sql = """
            SELECT 
                        ac.category_id,
                        ac.category_name AS assetType,
            
                        SUM(CASE WHEN s.status_name = 'Available' THEN 1 ELSE 0 END) AS available,
                        SUM(CASE WHEN s.status_name = 'Broken' THEN 1 ELSE 0 END) AS broken,
                        SUM(CASE WHEN s.status_name = 'Disposed' THEN 1 ELSE 0 END) AS disposed,
                        SUM(CASE WHEN s.status_name = 'In Use' THEN 1 ELSE 0 END) AS inUse,
                        SUM(CASE WHEN s.status_name = 'Lost' THEN 1 ELSE 0 END) AS lost,
                        SUM(CASE WHEN s.status_name = 'Maintenance' THEN 1 ELSE 0 END) AS maintenance,
            
                        COUNT(a.asset_id) AS total
            
                    FROM assets a
                    JOIN asset_categories ac ON a.category_id = ac.category_id
                    JOIN asset_status s ON a.status_id = s.status_id
            
                    GROUP BY ac.category_id, ac.category_name 
                    Order by ac.category_id ASC
        """;

            st = connection.prepareStatement(sql);
            rs = st.executeQuery(); //read -select

            while (rs.next()) {
                AssetTypeStatistic stat = new AssetTypeStatistic();

                stat.setTypeID(rs.getInt("category_id"));
                stat.setAssetType(rs.getString("assetType"));
                stat.setAvailable(rs.getInt("available"));
                stat.setBroken(rs.getInt("broken"));
                stat.setDisposed(rs.getInt("disposed"));
                stat.setInUse(rs.getInt("inUse"));
                stat.setLost(rs.getInt("lost"));
                stat.setMaintenance(rs.getInt("maintenance"));

                stat.setTotal(rs.getInt("total"));

                list.add(stat);
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public void createAsset(Asset a) {

        try {

            RoomDAO rdao = new RoomDAO();
            int storageId = rdao.getStorageRoomId();

            String sql = """
        INSERT INTO assets
        (asset_code, asset_name, category_id, room_id, serial_number, purchase_date, status_id)
        VALUES (?, ?, ?, ?, ?, ?, 1)
        """;

            st = connection.prepareStatement(sql);

            st.setString(1, a.getAssetCode());
            st.setString(2, a.getAssetName());
            st.setInt(3, a.getCategoryId());
            st.setInt(4, storageId);
            st.setString(5, a.getSerialNumber());
            if (a.getPurchaseDate() != null) {
                st.setDate(6, java.sql.Date.valueOf(a.getPurchaseDate()));
            } else {
                st.setNull(6, java.sql.Types.DATE);
            }
            st.executeUpdate();

        } catch (Exception e) {
            return;
        }
    }

    public void UpdateAsset(AssetDetail editAcc) {
        try {
            String sql = """
                         update assets set supplier = ?, price = ? where asset_code = ?
                        """;
            st = connection.prepareStatement(sql);
            //truyen tham so cho cau lenh sql
            st.setString(1, editAcc.getSupplier());
            st.setInt(2, editAcc.getPrice());
            st.setString(3, editAcc.getAssetCode());
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public List<Asset> getAssetsInStorage() {

        List<Asset> list = new ArrayList<>();

        try {

            String sql = """
        SELECT a.*
        FROM assets a
        JOIN rooms r ON a.room_id = r.room_id
        WHERE r.room_code = 'STORAGE'
        """;

            st = connection.prepareStatement(sql);

            rs = st.executeQuery();

            while (rs.next()) {

                Asset a = new Asset();

                a.setAssetId(rs.getInt("asset_id"));
                a.setAssetCode(rs.getString("asset_code"));
                a.setAssetName(rs.getString("asset_name"));
                list.add(a);
            }

        } catch (Exception e) {
            return null;
        }

        return list;
    }

    public List<Asset> getAssetsInStorageAll() {

        List<Asset> list = new ArrayList<>();

        try {

            String sql = """
        SELECT a.*
        FROM assets a
        JOIN rooms r ON a.room_id = r.room_id
        WHERE r.room_code = 'STORAGE'
        AND a.status_id = 1
        """;

            st = connection.prepareStatement(sql);

            rs = st.executeQuery();

            while (rs.next()) {

                Asset a = new Asset();

                a.setAssetId(rs.getInt("asset_id"));
                a.setAssetCode(rs.getString("asset_code"));
                a.setAssetName(rs.getString("asset_name"));

                list.add(a);
            }

        } catch (Exception e) {
            return null;
        }

        return list;
    }

    public void assignAssetToRoom(int assetId, int roomId) {

        try {

            String sql = """
        UPDATE assets
        SET room_id = ?, status_id = 2
        WHERE asset_id = ?
        """;

            st = connection.prepareStatement(sql);

            st.setInt(1, roomId);
            st.setInt(2, assetId);

            st.executeUpdate();

        } catch (Exception e) {
            return;
        }

    }

    public List<AssetStatus> getAllStatus() {

        List<AssetStatus> list = new ArrayList<>();

        String sql = "select *from asset_status ";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                AssetStatus s = new AssetStatus();

                s.setStatusId(rs.getInt("status_id"));
                s.setStatusName(rs.getString("status_name"));

                list.add(s);

            }

        } catch (Exception e) {
            return null;
        }

        return list;
    }

    public List<AssetCategory> getAllCategories() {

        List<AssetCategory> list = new ArrayList<>();

        try {

            String sql = "select *from asset_categories";

            st = connection.prepareStatement(sql);

            rs = st.executeQuery();

            while (rs.next()) {

                AssetCategory c = new AssetCategory();

                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));

                list.add(c);
            }

        } catch (Exception e) {
            return null;
        }

        return list;
    }

    public boolean isAssetCodeExist(String code) {

        try {

            String sql = "SELECT 1 FROM assets WHERE asset_code = ?";

            st = connection.prepareStatement(sql);
            st.setString(1, code);

            rs = st.executeQuery();

            if (rs.next()) {
                return true; // đã tồn tại
            }

        } catch (Exception e) {
            return false;
        }

        return false; // chưa tồn tại
    }

    public boolean isAssetSeriailExist(String code) {

        try {

            String sql = "SELECT 1 FROM assets WHERE serial_number = ?";

            st = connection.prepareStatement(sql);
            st.setString(1, code);

            rs = st.executeQuery();

            if (rs.next()) {
                return true; // đã tồn tại
            }

        } catch (Exception e) {
            return false;
        }

        return false; // chưa tồn tại
    }

    public StatisticAsset getStatisticAsset() {
        StatisticAsset sAsset = null;
        try {
            String sql = """
                         SELECT
                         SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) AS Available,
                         SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) AS InUse,
                         SUM(CASE WHEN status_id = 3 THEN 1 ELSE 0 END) AS Broken,
                         SUM(CASE WHEN status_id = 4 THEN 1 ELSE 0 END) AS Maintenance,
                         SUM(CASE WHEN status_id = 5 THEN 1 ELSE 0 END) AS Lost,
                         SUM(CASE WHEN status_id = 6 THEN 1 ELSE 0 END) AS Disposed
                         FROM assets;
                         """;
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                sAsset = new StatisticAsset(rs.getInt("Available"), rs.getInt("Broken"), rs.getInt("Disposed"), rs.getInt("InUse"), rs.getInt("Lost"), rs.getInt("Maintenance"));
            }
            return sAsset;
        } catch (Exception e) {
            return null;
        }
    }

    public List<AssetView> getAssetsByRoom(int roomID) {

        List<AssetView> list = new ArrayList<>();

        try {

            String sql = """
                     SELECT a.asset_id,
                            a.asset_name,
                            s.status_name
                     FROM assets a
                     JOIN asset_status s ON a.status_id = s.status_id
                     WHERE a.room_id = ?
                     """;

            st = connection.prepareStatement(sql);
            st.setInt(1, roomID);

            rs = st.executeQuery();

            while (rs.next()) {

                AssetView a = new AssetView(
                        rs.getInt("asset_id"),
                        rs.getString("asset_name"),
                        rs.getString("status_name")
                );

                list.add(a);
            }

        } catch (Exception e) {
           return null;
        }

        return list;
    }

    public List<AssetWithRoom> getAssetsByFilter(String roomId, String categoryId) {
        List<AssetWithRoom> list = new ArrayList<>();

        String sql = """
        SELECT a.asset_id, a.asset_name, s.status_name, r.room_code
        FROM assets a
        LEFT JOIN rooms r ON a.room_id = r.room_id
        JOIN asset_status s ON a.status_id = s.status_id
        WHERE 1=1
    """;

        if (roomId != null && !roomId.equals("")) {
            sql += " AND a.room_id = ?";
        }

        if (categoryId != null && !categoryId.equals("")) {
            sql += " AND a.category_id = ?";
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            int index = 1;

            if (roomId != null && !roomId.equals("")) {
                ps.setInt(index++, Integer.parseInt(roomId));
            }

            if (categoryId != null && !categoryId.equals("")) {
                ps.setInt(index++, Integer.parseInt(categoryId));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new AssetWithRoom(
                        rs.getInt("asset_id"),
                        rs.getString("asset_name"),
                        rs.getString("status_name"),
                        rs.getString("room_code")
                ));
            }

        } catch (Exception e) {
            return null;
        }

        return list;
    }

    public StatisticAsset getAssetStatusStats(String roomId, String categoryId) {
        StatisticAsset stats = new StatisticAsset();

        String sql = """
        SELECT 
            SUM(CASE WHEN s.status_name = 'Available' THEN 1 ELSE 0 END) AS available,
            SUM(CASE WHEN s.status_name = 'Broken' THEN 1 ELSE 0 END) AS broken,
            SUM(CASE WHEN s.status_name = 'Disposed' THEN 1 ELSE 0 END) AS disposed,
            SUM(CASE WHEN s.status_name = 'In Use' THEN 1 ELSE 0 END) AS inuse,
            SUM(CASE WHEN s.status_name = 'Lost' THEN 1 ELSE 0 END) AS lost,
            SUM(CASE WHEN s.status_name = 'Maintenance' THEN 1 ELSE 0 END) AS maintenance
        FROM assets a
        JOIN asset_status s ON a.status_id = s.status_id
        WHERE 1=1
    """;

        if (roomId != null && !roomId.isEmpty()) {
            sql += " AND a.room_id = " + roomId;
        }

        if (categoryId != null && !categoryId.isEmpty()) {
            sql += " AND a.category_id = " + categoryId;
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                stats.setAvailable(rs.getInt("available"));
                stats.setBroken(rs.getInt("broken"));
                stats.setDisposed(rs.getInt("disposed"));
                stats.setInUse(rs.getInt("inuse"));
                stats.setLost(rs.getInt("lost"));
                stats.setMaintenance(rs.getInt("maintenance"));
            }
        } catch (Exception e) {
            return null;
        }

        return stats;
    }

    public AssetEdit getAssetById(int id) {
        String sql = """
        SELECT a.asset_id, a.asset_code, a.asset_name,
               a.room_id, a.status_id,
               c.category_name,
               a.serial_number,
               CONVERT(VARCHAR, a.purchase_date, 23) as purchase_date
        FROM assets a
        JOIN asset_categories c ON a.category_id = c.category_id
        WHERE a.asset_id = ?
    """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AssetEdit a = new AssetEdit();
                a.setAssetId(rs.getInt("asset_id"));
                a.setAssetCode(rs.getString("asset_code"));
                a.setAssetName(rs.getString("asset_name"));
                a.setRoomId(rs.getInt("room_id"));
                a.setStatusId(rs.getInt("status_id"));
                a.setCategoryName(rs.getString("category_name"));
                a.setSerialNumber(rs.getString("serial_number"));
                a.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
                return a;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void updateAsset(AssetEdit a) {
        String sql = """
        UPDATE assets
        SET asset_code = ?,
            asset_name = ?,
            room_id = ?,
            status_id = ?
        WHERE asset_id = ?
    """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, a.getAssetCode());
            ps.setString(2, a.getAssetName());
            ps.setInt(3, a.getRoomId());
            ps.setInt(4, a.getStatusId());
            ps.setInt(5, a.getAssetId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isInUseOrMaintenance(int assetId) {
        String sql = """
        SELECT status_id FROM assets WHERE asset_id = ?
    """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, assetId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int status = rs.getInt("status_id");
                return (status == 2 || status == 4); // In Use hoặc Maintenance
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void deleteAsset(int assetId) {
        String sql = "DELETE FROM assets WHERE asset_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, assetId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AssetDetail getAssetDetail(int assetId) {
        AssetDetail a = null;
        try {
            String sql = """
            SELECT 
                a.asset_id,
                a.asset_code,
                a.asset_name,
                r.room_code,
                c.category_name,
                s.status_name,
                a.purchase_date,
                a.supplier,
                a.price
            FROM assets a
            LEFT JOIN rooms r ON a.room_id = r.room_id
            LEFT JOIN asset_categories c ON a.category_id = c.category_id
            LEFT JOIN asset_status s ON a.status_id = s.status_id
            WHERE a.asset_id = ?
        """;

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, assetId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                a = new AssetDetail();
                a.setAssetId(rs.getInt("asset_id"));
                a.setAssetCode(rs.getString("asset_code"));
                a.setAssetName(rs.getString("asset_name"));
                a.setRoomName(rs.getString("room_code"));
                a.setCategoryName(rs.getString("category_name"));
                a.setStatusName(rs.getString("status_name"));

                if (rs.getDate("purchase_date") != null) {
                    a.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
                }

                a.setSupplier(rs.getString("supplier"));
                a.setPrice(rs.getInt("price"));
            }

        } catch (Exception e) {
            return null;
        }
        return a;
    }

    public List<MaintenanceHistory> getMaintenanceHistory(int assetId) {
        List<MaintenanceHistory> list = new ArrayList<>();

        String sql = """
        SELECT 
            mr.created_at,
            mr.description,
            ms.status_name,
            u1.full_name AS reported_by_name,
            u2.full_name AS technician_name,
            mr.completed_at
        FROM maintenance_requests mr
        JOIN users u1 ON mr.reported_by = u1.user_id
        LEFT JOIN users u2 ON mr.technician_id = u2.user_id
        JOIN maintenance_status ms ON mr.status_id = ms.status_id
        WHERE mr.asset_id = ?
        ORDER BY mr.created_at DESC
    """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, assetId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MaintenanceHistory m = new MaintenanceHistory();

                m.setDate(rs.getTimestamp("created_at"));
                m.setProblem(rs.getString("description"));
                m.setStatus(rs.getString("status_name"));
                m.setReportedBy(rs.getString("reported_by_name"));
                m.setTechnician(rs.getString("technician_name"));
                m.setCompletedAt(rs.getString("completed_at"));

                list.add(m);
            }

        } catch (Exception e) {
            return null;
        }

        return list;
    }

    public List<AssetType> getAssetTypes() {
        List<AssetType> accs = new ArrayList<>();
        try {
            String sql = "select * from asset_categories";
            st = connection.prepareStatement(sql);
            //truyentham so cho cau lenh
            rs = st.executeQuery(); //read - select
            while (rs.next()) {
                int typeId = rs.getInt("category_id");
                String typeName = rs.getString("category_name");
                System.out.println(typeId);
                System.out.println(typeName);
                AssetType acc = new AssetType(typeId, typeName);
                accs.add(acc);
            }
            return accs;
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteAssetType(int id) {
        String sql = "DELETE FROM asset_categories WHERE category_id = ?";
        try {
            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }
    
    public void createTypeAsset(String newAcc) {
        try {
            String sql = """
                         INSERT INTO asset_categories (category_name)
                         VALUES (?)
                         """;
            st = connection.prepareStatement(sql);
            //truyen tham so cho cau lenh sql
            st.setString(1, newAcc);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

}
