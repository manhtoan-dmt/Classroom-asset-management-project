/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.AssetDAO;
import dal.BookingDAO;
import dal.RoomDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.AssetView;
import model.BookingView;
import model.Room;
import model.RoomView;

/**
 *
 * @author admin
 */
public class RoomuserController extends HttpServlet {

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
            out.println("<title>Servlet RoomuserController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RoomuserController at " + request.getContextPath() + "</h1>");
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
        RoomDAO dao = new RoomDAO();

        if (action == null) {
            String keyword = request.getParameter("keyword");
            List<RoomView> list;
            if (keyword == null || keyword.trim().isEmpty()) {
                list = dao.getAllRooms();
            } else {
                list = dao.searchRoom(keyword);
            }
            request.setAttribute("rooms", list);

            request.getRequestDispatcher("/views_student_teacher/room.jsp").forward(request, response);
        }else if (action.equals("view")) {

            int id = Integer.parseInt(request.getParameter("id"));

            RoomDAO roomD = new RoomDAO();
            AssetDAO assetDAO = new AssetDAO();
            BookingDAO bookingDAO = new BookingDAO();

            RoomView room = roomD.getRoomByID(id);
            List<AssetView> assets = assetDAO.getAssetsByRoom(id);
            List<BookingView> bookings = bookingDAO.getTodayBookingByRoom(id);

            request.setAttribute("room", room);
            request.setAttribute("assets", assets);
            request.setAttribute("bookings", bookings);

            request.getRequestDispatcher("/views_student_teacher/ViewRoomUser.jsp")
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
