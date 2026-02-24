package com.hms.controller;

import com.hms.dao.RoomDAO;
import com.hms.model.Room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/rooms")
public class UserRoomsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RoomDAO roomDAO;

    @Override
    public void init() throws ServletException {
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Fetch all rooms added by admin
        List<Room> rooms = roomDAO.getAllRooms();

        // ✅ Send rooms to JSP
        request.setAttribute("availableRooms", rooms);

        // ✅ Forward to user rooms page
        request.getRequestDispatcher("/jsp/user/rooms.jsp")
               .forward(request, response);
    }
}