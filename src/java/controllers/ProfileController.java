/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author THIN15
 */
public class ProfileController extends HttpServlet {

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
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
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
    UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("account");

        if (u == null) {
            response.sendRedirect("login");
            return;
        }

        User user = dao.getUserProfileById(u.getUserId());

        request.setAttribute("user", user);
        request.getRequestDispatcher("views_profile/Profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("account");

        if (u == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = u.getUserId();

        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        boolean profileUpdated = false;
        boolean passwordChanged = false;

        // ========================
        // VALIDATE
        // ========================
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("error", "Username cannot be empty!");
            doGet(request, response);
            return;
        }

        if (fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("error", "Name cannot be empty!");
            doGet(request, response);
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email cannot be empty!");
            doGet(request, response);
            return;
        }

        if (phone == null || phone.trim().isEmpty()) {
            request.setAttribute("error", "Phone cannot be empty!");
            doGet(request, response);
            return;
        }

        // ========================
        // CHECK TRÙNG
        // ========================
        if (dao.isUsernameExists(username, userId)) {
            request.setAttribute("error", "Username already exists!");
            doGet(request, response);
            return;
        }

        if (dao.isFullNameExists(fullName, userId)) {
            request.setAttribute("error", "Name already exists!");
            doGet(request, response);
            return;
        }

        if (dao.isEmailExists(email, userId)) {
            request.setAttribute("error", "Email already exists!");
            doGet(request, response);
            return;
        }

        if (dao.isPhoneExists(phone, userId)) {
            request.setAttribute("error", "Phone already exists!");
            doGet(request, response);
            return;
        }

        // ========================
        // UPDATE PROFILE
        // ========================
        profileUpdated = dao.updateProfile(userId, username, fullName, email, phone);

        // ========================
        // CHANGE PASSWORD
        // ========================
        if (currentPassword != null && !currentPassword.isEmpty()
                && newPassword != null && !newPassword.isEmpty()) {

            if (!dao.checkPassword(userId, currentPassword)) {
                request.setAttribute("error", "Wrong current password!");
                doGet(request, response);
                return;
            }

            passwordChanged = dao.changePassword(userId, newPassword);
        }

        // ========================
        // UPDATE SESSION (RẤT QUAN TRỌNG)
        // ========================
        User updatedUser = dao.getUserById(userId);
        session.setAttribute("account", updatedUser);

        // ========================
        // MESSAGE
        // ========================
        if (profileUpdated && passwordChanged) {
            request.setAttribute("success", "Profile & Password updated!");
        } else if (profileUpdated) {
            request.setAttribute("success", "Profile updated!");
        } else if (passwordChanged) {
            request.setAttribute("success", "Password changed!");
        } else {
            request.setAttribute("error", "Nothing changed!");
        }

        doGet(request, response);
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
