package controllers;

import dal.BookingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.BookView;
import model.User;

public class MyBookingsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

            case "list":
                loadMyBookings(request, response);
                break;

            case "cancel":
                cancelBooking(request, response);
                break;

            default:
                loadMyBookings(request, response);
                break;
        }
    }

    private void loadMyBookings(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getUserId();

        BookingDAO dao = new BookingDAO();

        List<BookView> list = dao.getBookingByUser(userId);

        request.setAttribute("booking", list);

        request.getRequestDispatcher("/views_student_teacher/MyBookingUser.jsp")
                .forward(request, response);
    }

    private void cancelBooking(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int bookingId = Integer.parseInt(request.getParameter("id"));

       HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getUserId();

        BookingDAO dao = new BookingDAO();
        dao.cancelBooking(bookingId, userId);

        response.sendRedirect("mybooking?action=list");
    }
}
