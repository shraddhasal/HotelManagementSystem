package com.hms.controller;

import com.hms.dao.RoomDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin/room/delete")
public class DeleteRoomServlet extends HttpServlet {

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

        try {
            // ✅ Read room ID from URL
            String idParam = request.getParameter("id");

            if (idParam != null && !idParam.isEmpty()) {
                long roomId = Long.parseLong(idParam);

                // ✅ Delete room via DAO
                roomDAO.delete(roomId);
            }

            // ✅ Redirect back to Manage Rooms page
            response.sendRedirect(request.getContextPath() + "/admin/rooms");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/rooms");
        }
    }
}