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
        // THỐNG NHẤT: Dùng "account" (hoặc "user") tùy theo lúc bạn Login bạn set tên gì
        User user = (User) session.getAttribute("account"); 

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        SupportDAO dao = new SupportDAO();

        // 1. Lấy dữ liệu cho các ô Select trên Form (Phòng và Thiết bị)
        List<Room> roomList = dao.getAllRooms();
        List<Asset> assetList = dao.getAllAssets();
        
        // 2. Lấy lịch sử báo cáo của chính User đó (Dòng này cực kỳ quan trọng)
        List<MaintenanceRequestView> history = dao.getRequestHistory(user.getUserId());

        // 3. Đẩy toàn bộ dữ liệu sang JSP
        request.setAttribute("roomList", roomList);
        request.setAttribute("assetList", assetList);
        request.setAttribute("requestHistory", history); // Tên này phải khớp với c:forEach bên JSP

        // 4. Mở trang JSP (Kiểm tra kỹ đường dẫn này)
        request.getRequestDispatcher("/views_student_teacher/support.jsp").forward(request, response);
    }

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("account");

    // 1. Chống lỗi NullPointerException nếu Session hết hạn
    if (user == null) {
        request.setAttribute("errorMsg", "Phiên đăng nhập hết hạn, vui lòng login lại!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
        return;
    }

    try {
        // 2. Lấy dữ liệu từ Form (roomId và assetId giờ đều là int)
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        int assetId = Integer.parseInt(request.getParameter("assetId"));
        String description = request.getParameter("description");
        int userId = user.getUserId();

        // 3. Gọi DAO xử lý
        SupportDAO dao = new SupportDAO();
        boolean isSuccess = dao.insertRequest(roomId, assetId, userId, description);

        // 4. Thiết lập thông báo Alert
        if (isSuccess) {
            request.setAttribute("successMsg", "Gửi yêu cầu hỗ trợ thành công!");
        } else {
            request.setAttribute("errorMsg", "Không thể lưu yêu cầu vào Database.");
        }
        
    } catch (Exception e) {
        request.setAttribute("errorMsg", "Lỗi nhập liệu: " + e.getMessage());
    }

    // 5. Quay về doGet để load lại danh sách lịch sử mới nhất
    doGet(request, response);
}
}

