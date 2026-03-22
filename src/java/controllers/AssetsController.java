/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.AssetTypeStatistic;
import dal.AssetDAO;
import dal.RoomDAO;
import model.Asset;
import model.AssetDetail;
import model.AssetEdit;
import model.Room;
import model.AssetWithRoom;
import model.MaintenanceHistory;
import model.StatisticAsset;

/**
 *
 * @author ADMIN
 */
public class AssetsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AssetsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AssetsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int assetId = 0;
        AssetDAO dao = new AssetDAO();

        if (action == null) {

            List<AssetTypeStatistic> list = dao.getAssetStatistics();

            request.setAttribute("assetStats", list);

            request.getRequestDispatcher("views_asset_management/AssetManagement.jsp")
                    .forward(request, response);
        } else if (action.equals("create")) {

            request.setAttribute("categories", dao.getAllCategories());
            request.setAttribute("statuses", dao.getAllStatus());
            RoomDAO rdao = new RoomDAO();
//            request.setAttribute("rooms", rdao.getAllRoomsType());

            request.getRequestDispatcher("views_asset_management/CreateNewProduct.jsp")
                    .forward(request, response);

        } else if (action.equals("add")) {

            List<Asset> storageAssets = dao.getAssetsInStorage();
            RoomDAO rdao = new RoomDAO();

            request.setAttribute("storageAssets", storageAssets);
            request.setAttribute("rooms", rdao.getAllRoomsType());

            request.getRequestDispatcher("views_asset_management/AddAsset.jsp")
                    .forward(request, response);
        } else if (action.equals("view")) {

            String roomId = request.getParameter("roomId");
            String categoryId = request.getParameter("categoryId");

            RoomDAO rdao = new RoomDAO();

            List<AssetWithRoom> assets = dao.getAssetsByFilter(roomId, categoryId);
            StatisticAsset stats = dao.getAssetStatusStats(roomId, categoryId);

            request.setAttribute("assets", assets);
            request.setAttribute("stats", stats);

            request.setAttribute("rooms", rdao.getRoomsWithCode());
            request.setAttribute("assetTypes", dao.getAllCategories());

            request.setAttribute("selectedRoomId", roomId);
            request.setAttribute("selectedCategoryId", categoryId);

            request.getRequestDispatcher("views_asset_management/ViewAssetRoom.jsp")
                    .forward(request, response);
        } else if (action.equals("edit")) {
            assetId = Integer.parseInt(request.getParameter("assetId"));

            AssetEdit asset = dao.getAssetById(assetId);
            RoomDAO rdao = new RoomDAO();

            request.setAttribute("asset", asset);
            request.setAttribute("rooms", rdao.getAllRoomsType());
            request.setAttribute("statuses", dao.getAllStatus());

            request.getRequestDispatcher("views_asset_management/EditAsset.jsp")
                    .forward(request, response);
        } else if (action.equals("delete")) {

            String idRaw = request.getParameter("assetId");

            assetId = 0;
            try {
                assetId = Integer.parseInt(idRaw.trim());
            } catch (Exception e) {
                response.sendRedirect("Assets?action=view");
                return;
            }

            // Check không cho xóa nếu đang In Use hoặc Maintenance
            if (dao.isInUseOrMaintenance(assetId)) {
                response.sendRedirect("Assets?action=view&error=CannotDelete");
                return;
            }

            dao.deleteAsset(assetId);

            response.sendRedirect("Assets?action=view");
        } else if (action.equals("detail")) {

            String idRaw = request.getParameter("assetId");

            assetId = 0;
            try {
                assetId = Integer.parseInt(idRaw.trim());
            } catch (Exception e) {
                response.sendRedirect("Assets?action=view");
                return;
            }

            
            AssetDetail asset = dao.getAssetDetail(assetId);
            if(asset == null) {
                response.sendRedirect("Assets?action=view");
                return;
            }
            List<MaintenanceHistory> list = dao.getMaintenanceHistory(assetId);

            request.setAttribute("asset", asset);
            request.setAttribute("maintenanceList", list);

            request.getRequestDispatcher("/views_asset_management/ViewDetailAsset.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
