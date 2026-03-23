/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.AssetDAO;
import dal.RoomDAO;
import dal.SupportDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Asset;
import model.AssetSummary;
import model.MaintenanceRequestView;
import model.Room;
import model.RoomView;
import model.User;

/**
 *
 * @author admin
 */

public class SupportServlet extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account"); 

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        SupportDAO dao = new SupportDAO();

        List<Room> roomList = dao.getAllRooms();
        List<Asset> assetList = dao.getAllAssets();
        
        List<MaintenanceRequestView> history = dao.getRequestHistory(user.getUserId());

        request.setAttribute("roomList", roomList);
        request.setAttribute("assetList", assetList);
        request.setAttribute("requestHistory", history); 

        request.getRequestDispatcher("/views_student_teacher/support.jsp").forward(request, response);
    }

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("account");

    if (user == null) {
        request.setAttribute("errorMsg", "Phiên đăng nhập hết hạn, vui lòng login lại!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
        return;
    }

    try {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        int assetId = Integer.parseInt(request.getParameter("assetId"));
        String description = request.getParameter("description");
        int userId = user.getUserId();

        SupportDAO dao = new SupportDAO();
        boolean isSuccess = dao.insertRequest(roomId, assetId, userId, description);

        if (isSuccess) {
            request.setAttribute("successMsg", "Gửi yêu cầu hỗ trợ thành công!");
        } else {
            request.setAttribute("errorMsg", "Không thể lưu yêu cầu vào Database.");
        }
        
    } catch (Exception e) {
        request.setAttribute("errorMsg", "Lỗi nhập liệu: " + e.getMessage());
    }

    doGet(request, response);
}
}

