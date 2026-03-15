package controllers;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.User;

public class LoginController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.login(username, password);

        if (user == null) {

            request.setAttribute("error", "Username hoặc Password sai!");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {

            HttpSession session = request.getSession();
            session.setAttribute("account", user);

            int role = user.getRoleId();

            switch (role) {

                case 1:
                    response.sendRedirect("admin-dashboard");
                    break;

                case 2:
                    response.sendRedirect("staff-dashboard");
                    break;

                case 3:
                    response.sendRedirect("teacher-dashboard");
                    break;

                case 4:
                    response.sendRedirect("student-dashboard");
                    break;

                default:
                    response.sendRedirect("login");
            }
        }
    }
}