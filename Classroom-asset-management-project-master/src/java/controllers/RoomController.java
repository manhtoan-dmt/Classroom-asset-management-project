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
import model.RoomWithName;

/**
 *
 * @author ADMIN
 */
public class RoomController extends HttpServlet {

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
            out.println("<title>Servlet RoomController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RoomController at " + request.getContextPath() + "</h1>");
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

            request.getRequestDispatcher("/views_room_management/RoomManagement.jsp")
                    .forward(request, response);
        } else if (action.equals("view")) {

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

            request.getRequestDispatcher("/views_room_management/ViewRoomManagement.jsp")
                    .forward(request, response);
        } else if ("edit".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));

            Room room = dao.getRoomById(id);

            request.setAttribute("room", room);

            request.setAttribute("types", dao.getAllTypes());
            request.setAttribute("statuses", dao.getAllStatus());
            request.setAttribute("locations", dao.getAllBuildings());

            request.getRequestDispatcher("/views_room_management/EditRoom.jsp")
                    .forward(request, response);
        } else if ("add".equals(action)) {
            request.setAttribute("types", dao.getAllTypes());
            request.setAttribute("statuses", dao.getAllStatus());
            request.setAttribute("locations", dao.getAllBuildings());
            request.getRequestDispatcher("views_room_management/AddNewRoom.jsp")
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
        String action = request.getParameter("action");
        RoomDAO roomDAO = new RoomDAO();
        if ("update".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("roomCode");

            // CHECK TRÙNG
            if (roomDAO.isRoomCodeExist(name, id)) {

                request.setAttribute("errorName", "Room name already exists");

                Room room = roomDAO.getRoomById(id);

                request.setAttribute("room", room);
                request.setAttribute("types", roomDAO.getAllTypes());
                request.setAttribute("statuses", roomDAO.getAllStatus());
                request.setAttribute("locations", roomDAO.getAllBuildings());

                request.getRequestDispatcher("views_room_management/EditRoom.jsp")
                        .forward(request, response);
                return;
            }

            int capacity = Integer.parseInt(request.getParameter("capacity"));
            int type = Integer.parseInt(request.getParameter("typeId"));
            int location = Integer.parseInt(request.getParameter("buildingId"));
            int status = Integer.parseInt(request.getParameter("statusId"));

            Room r = new Room(id, name, capacity, location, type, status);

            roomDAO.updateRoom(r);

            response.sendRedirect("Room");
        }
        if ("add".equals(action)) {
            String roomCode = request.getParameter("roomCode");

            if (roomDAO.isRoomCodeExist(roomCode, 0)) {

                request.setAttribute("error", "Room code already exists");

                request.getRequestDispatcher("views_room_management/AddRoom.jsp")
                        .forward(request, response);

                return;
            }
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            int buildingId = Integer.parseInt(request.getParameter("buildingId"));
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            int statusId = Integer.parseInt(request.getParameter("statusId"));

            Room r = new Room(0, roomCode, capacity, buildingId, typeId, statusId);

            roomDAO.addRoom(r);

            response.sendRedirect("Room");
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
