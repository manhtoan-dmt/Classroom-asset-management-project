/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.BookingDAO;
import dal.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import model.BookView;
import model.BookingDetail;

/**
 *
 * @author THIN15
 */
public class BookingController extends HttpServlet {

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
            out.println("<title>Servlet BookingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingController at " + request.getContextPath() + "</h1>");
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
        BookingDAO bDAO = new BookingDAO();

        if (action == null) {
            action = "manage";
        }
        

        switch (action) {
            case "detail":
                int id = Integer.parseInt(request.getParameter("id"));

                BookingDetail b = bDAO.getBookingDetailById(id);

                request.setAttribute("b", b);

                request.getRequestDispatcher("views_booking_management/BookingDetail.jsp")
                        .forward(request, response);
                break;

            case "create":
                RoomDAO roomDAO = new RoomDAO();

                request.setAttribute("rooms", roomDAO.getAllRoomById(1));

                request.getRequestDispatcher("views_booking_management/CreateNewBooking.jsp")
                        .forward(request, response);

                break;
            case "updateStatus":
                int idd = Integer.parseInt(request.getParameter("id"));
                int status = Integer.parseInt(request.getParameter("status"));
                bDAO.updateStatus(idd, status);
                response.sendRedirect("Book?action=detail&id=" + idd);
                break;
                
            default:
                String date = request.getParameter("date");

                if (date == null || date.isEmpty()) {
                    date = LocalDate.now().toString();
                }
                try {
                    List<BookView> list = bDAO.getBookingsByDate(date);
                    request.setAttribute("bookings", list);
                    request.setAttribute("today", date);
                    request.getRequestDispatcher("views_booking_management/BookingManagement.jsp")
                            .forward(request, response);
                } catch (Exception e) {
                    return;
                }
                break;
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
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            String date = request.getParameter("date");
            String slot = request.getParameter("timeSlot");
            String purpose = request.getParameter("purpose");

            String[] parts = slot.split("-");
// định dạng Timestamp yyyy-mm-dd hh:mm:ss
            String start = date + " " + parts[0] + ":00:00";
            String end = date + " " + parts[1] + ":00:00";

            Timestamp startTime = Timestamp.valueOf(start);
            Timestamp endTime = Timestamp.valueOf(end);

            int userId = 5; // tạm thời (sau sẽ lấy từ session)
            BookingDAO dao = new BookingDAO();

            dao.createBooking(roomId, userId, startTime, endTime, purpose);

            response.sendRedirect("Book");
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
