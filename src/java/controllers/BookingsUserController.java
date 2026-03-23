/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.BookingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import model.User;

/**
 *
 * @author admin
 */
public class BookingsUserController extends HttpServlet {

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
            out.println("<title>Servlet BookingsUserController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingsUserController at " + request.getContextPath() + "</h1>");
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
         String roomIdRaw = request.getParameter("roomId");

    if (roomIdRaw != null) {
        int roomId = Integer.parseInt(roomIdRaw);

        request.setAttribute("room", new dal.RoomDAO().getRoomById(roomId));
    }
System.out.println("roomId = " + roomIdRaw);
    request.getRequestDispatcher("/views_student_teacher/CreateBookingsUser.jsp")
            .forward(request, response);
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
        String action = request.getParameter("action");
       if ("create".equals(action)) {

    int roomId = Integer.parseInt(request.getParameter("roomId"));
    String date = request.getParameter("date");
    String slot = request.getParameter("timeSlot");
    String purpose = request.getParameter("purpose");

    String[] parts = slot.split("-");

    String start = date + " " + parts[0] + ":00:00";
    String end = date + " " + parts[1] + ":00:00";

    Timestamp startTime = Timestamp.valueOf(start);
    Timestamp endTime = Timestamp.valueOf(end);

     HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getUserId();

    BookingDAO dao = new BookingDAO();

    boolean isConflict = dao.isRoomBookedApproved(roomId, startTime, endTime);

    if (isConflict) {
        request.setAttribute("error", "THE ROOM IS ALREADY OCCUPIED");

        request.setAttribute("room", new dal.RoomDAO().getRoomById(roomId));

        request.getRequestDispatcher("/views_student_teacher/CreateBookingsUser.jsp")
                .forward(request, response);
        return;
    }

    dao.createBooking(roomId, userId, startTime, endTime, purpose);

    response.sendRedirect("roomuser");
}
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
