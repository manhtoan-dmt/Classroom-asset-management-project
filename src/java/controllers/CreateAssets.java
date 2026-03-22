/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.AssetDAO;
import dal.RoomDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import model.Asset;

/**
 *
 * @author ADMIN
 */
public class CreateAssets extends HttpServlet {

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
            out.println("<title>Servlet CreateAssets</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateAssets at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        AssetDAO dao = new AssetDAO();

        String code = request.getParameter("assetCode");
        if (dao.isAssetCodeExist(code)) {

            request.setAttribute("error", "Asset Code already exists!");
            request.setAttribute("categories", dao.getAllCategories());
            request.setAttribute("statuses", dao.getAllStatus());
            RoomDAO rdao = new RoomDAO();                     // thêm
            request.setAttribute("rooms", rdao.getAllRoomsType());   // thêm
            request.getRequestDispatcher("views_asset_management/CreateNewProduct.jsp")
                    .forward(request, response);

            return;
        }

        String name = request.getParameter("assetName");

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

//        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String serial = request.getParameter("serialNumber");
        if (dao.isAssetSeriailExist(serial)) {

            request.setAttribute("error", "Serial Number already exists!");
            request.setAttribute("categories", dao.getAllCategories());
            request.setAttribute("statuses", dao.getAllStatus());
            RoomDAO rdao = new RoomDAO();                     // thêm
            request.setAttribute("rooms", rdao.getAllRoomsType());   // thêm
            request.getRequestDispatcher("views_asset_management/CreateNewProduct.jsp")
                    .forward(request, response);

            return;
        }

        String dateStr = request.getParameter("purchaseDate");

        LocalDate purchaseDate = null;

        if (dateStr != null && !dateStr.isEmpty()) {
            purchaseDate = LocalDate.parse(dateStr);
        }

        int statusId = 1;

        Asset a = new Asset();

        a.setAssetCode(code);
        a.setAssetName(name);
        a.setCategoryId(categoryId);
//        a.setRoomId(roomId);
        a.setSerialNumber(serial);
        a.setPurchaseDate(purchaseDate);
        a.setStatusId(statusId);

        dao.createAsset(a);

        response.sendRedirect("Assets");
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
