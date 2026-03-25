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
import java.time.LocalDateTime;
import java.util.List;
import model.User;

/**
 *
 * @author THIN15
 */
public class UserManagementController extends HttpServlet {

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
            out.println("<title>Servlet UserManagementController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserManagementController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        int role = user.getRoleId();
        String action = request.getParameter("action");

        UserDAO userDAO = new UserDAO();
        //cua admin
        if (role == 1) {
            if (action == null) {
                action = "default";
            }

            switch (action) {
                case "view":
                    int id = Integer.parseInt(request.getParameter("id"));
                    User u = userDAO.getUserById(id);
                    request.setAttribute("user", u);
                    request.getRequestDispatcher("/views_user_management/ViewUser.jsp").forward(request, response);
                    return;
                case "edit":
                    int id1 = Integer.parseInt(request.getParameter("id"));

                    User userEdit = userDAO.getUserById(id1);
                    request.setAttribute("user", userEdit);
                    request.getRequestDispatcher("views_user_management/EditUser.jsp").forward(request, response);
                    return;
                case "delete":
                    int id2 = Integer.parseInt(request.getParameter("id"));
                    UserDAO dao = new UserDAO();
                    dao.deleteUser(id2);
                    response.sendRedirect("UserManagement");
                    return;
                case "create":
                    request.getRequestDispatcher("/views_user_management/CreateNewUser.jsp").forward(request, response);
                    return;
                default:
                    String keyword = request.getParameter("keyword");
                    request.setAttribute("keyword", keyword);
                    List<User> lUser = userDAO.getUsers();
                    if (keyword != null && !keyword.trim().isEmpty()) {
                        lUser = userDAO.searchUsers(keyword);
                    } else {
                        lUser = userDAO.getUsers();
                    }
                    request.setAttribute("lUser", lUser);
                    request.getRequestDispatcher("/views_user_management/UserManagement.jsp").forward(request, response);
            }

        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
        UserDAO dao = new UserDAO();
        String action = request.getParameter("action");
        String username;
        String password;
        String fullName;
        String email;
        String phone;
        int roleId;
        int statusId;

        switch (action) {
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                username = request.getParameter("username");
                password = request.getParameter("password");
                fullName = request.getParameter("fullName");
                email = request.getParameter("email");
                phone = request.getParameter("phone");

                roleId = Integer.parseInt(request.getParameter("role"));
                statusId = Integer.parseInt(request.getParameter("status"));
                username = username.replaceAll("\\s+", "");
                phone = phone.replaceAll("\\s+", "");
                User u = new User(id, username, password, fullName, email, phone, roleId, statusId, LocalDateTime.MIN);
                dao.updateUser(u);
                response.sendRedirect("UserManagement");
                return;
            case "create":
                username = request.getParameter("username");
                password = request.getParameter("password");
                fullName = request.getParameter("fullName");
                email = request.getParameter("email");
                phone = request.getParameter("phone");

                roleId = Integer.parseInt(request.getParameter("roleId"));
                statusId = Integer.parseInt(request.getParameter("statusId"));
                // CHECK trung
                String error = dao.isUsernameExist(username, email, phone);
                if (error != null) {

                    request.setAttribute("error", error);

                    request.getRequestDispatcher("views_user_management/CreateNewUser.jsp")
                            .forward(request, response);
                }
                username = username.replaceAll("\\s+", "");
                phone = phone.replaceAll("\\s+", "");
                dao.insertUser(username, password, fullName, email, phone, roleId, statusId);

                response.sendRedirect("UserManagement");
                return;

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
