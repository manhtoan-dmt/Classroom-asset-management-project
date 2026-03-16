/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.AssetDAO;
import dal.IssueDAO;
import dal.RoomDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import model.StatisticAsset;
import model.StatisticIssue;
import model.StatisticRoom;
import model.StatisticUser;
import model.User;
import org.apache.catalina.Session;

/**
 *
 * @author THIN15
 */
public class HomeController extends HttpServlet {

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
            out.println("<title>Servlet HomeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
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

        //cua admin vơi room management
        if (role == 1 || role == 2) {
            String da = request.getParameter("nDate");
            if (da == null || da.isEmpty()) {
                da = LocalDate.now().toString();
            }

            StatisticRoom sRoom = new StatisticRoom();
            RoomDAO romDAO = new RoomDAO();
            sRoom = romDAO.getStatisticRoom();
            request.setAttribute("sRoom", sRoom);

            AssetDAO assetDAO = new AssetDAO();
            StatisticAsset sAsset = assetDAO.getStatisticAsset();
            request.setAttribute("sAsset", sAsset);

            IssueDAO isDAO = new IssueDAO();
            StatisticIssue sIssue = isDAO.getStatisticIssue(da);
            request.setAttribute("sIssue", sIssue);

            UserDAO userDAO = new UserDAO();
            StatisticUser sUser = userDAO.getStatisticUser();
            request.setAttribute("sUser", sUser);

            request.getRequestDispatcher("/views_home/Home.jsp").forward(request, response);
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
