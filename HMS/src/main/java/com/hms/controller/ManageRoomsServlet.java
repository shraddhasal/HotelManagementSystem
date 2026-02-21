package com.hms.controller;

import com.hms.dao.RoomDAO;
import com.hms.model.Room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/rooms")
public class ManageRoomsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RoomDAO roomDAO;

    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Admin session check
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminName") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/admin/login.jsp");
            return;
        }

        // ✅ Fetch all rooms from DAO
        List<Room> rooms = roomDAO.getAllRooms();

        // ✅ Send data to JSP
        request.setAttribute("rooms", rooms);

        // ✅ Forward to rooms.jsp
        request.getRequestDispatcher("/jsp/admin/rooms.jsp")
               .forward(request, response);
    }
}