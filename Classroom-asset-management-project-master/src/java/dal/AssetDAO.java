package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.Asset;
import model.AssetSummary;
import model.AssetCategory;
import model.AssetStatus;
import model.AssetStatusHistory;
import model.AssetView;
import model.StatisticAsset;

public class AssetDAO extends DBContext {

    PreparedStatement st;
    ResultSet rs;

    public List<AssetSummary> getAssetSummarys(int roomId) {
        List<AssetSummary> listAssets = new ArrayList<>();
        try {
            String sql = """
            SELECT 
            a.category_id,
            a.asset_name,
            SUM(CASE WHEN ast.status_name = 'Available' THEN a.quantity ELSE 0 END) AS good,
            SUM(CASE WHEN ast.status_name = 'Broken' THEN a.quantity ELSE 0 END) AS broken,
            SUM(CASE WHEN ast.status_name = 'Maintenance' THEN a.quantity ELSE 0 END) AS repair,
            SUM(a.quantity) AS total
        FROM assets a
        JOIN asset_status ast ON a.status_id = ast.status_id
        WHERE  a.room_id = ?
        GROUP BY a.category_id, a.asset_name
        ORDER BY a.category_id
                        """;
            st = connection.prepareStatement(sql);
            st.setInt(1, roomId);

            rs = st.executeQuery(); //read -select
            while (rs.next()) {
                AssetSummary a = new AssetSummary();

                a.setCategoryId(rs.getInt("category_id"));
                a.setAssetName(rs.getString("asset_name"));
                a.setGood(rs.getInt("good"));
                a.setBroken(rs.getInt("broken"));
                a.setRepair(rs.getInt("repair"));
                a.setTotal(rs.getInt("total"));

                listAssets.add(a);
            }
            return listAssets;

        } catch (Exception e) {
            return null;
        }
    }

    public List<AssetSummary> getAllAssets() {

        List<AssetSummary> list = new ArrayList<>();

        String sql = """
        SELECT 
            a.category_id,
            a.asset_name,
            SUM(CASE WHEN ast.status_name = 'Available' THEN a.quantity ELSE 0 END) AS good,
            SUM(CASE WHEN ast.status_name = 'Broken' THEN a.quantity ELSE 0 END) AS broken,
            SUM(CASE WHEN ast.status_name = 'Maintenance' THEN a.quantity ELSE 0 END) AS repair,
            SUM(a.quantity) AS total
        FROM assets a
        JOIN asset_status ast 
            ON a.status_id = ast.status_id
        GROUP BY 
            a.category_id,
            a.asset_name
        ORDER BY 
            a.category_id
    """;

        try {

            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                AssetSummary a = new AssetSummary();

                a.setCategoryId(rs.getInt("category_id"));
                a.setAssetName(rs.getString("asset_name"));
                a.setGood(rs.getInt("good"));
                a.setBroken(rs.getInt("broken"));
                a.setRepair(rs.getInt("repair"));
                a.setTotal(rs.getInt("total"));

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
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
            e.printStackTrace();
        }

        return list;
    }
}
