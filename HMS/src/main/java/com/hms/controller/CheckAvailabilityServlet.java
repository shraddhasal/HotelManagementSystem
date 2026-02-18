package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/checkAvailability")
public class CheckAvailabilityServlet extends HttpServlet {

    private static final String DB_URL =
        "jdbc:mysql://localhost:3306/hms";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String location = request.getParameter("location");
        String checkIn = request.getParameter("checkin");
        String checkOut = request.getParameter("checkout");
        String adults = request.getParameter("adults");
        String rooms = request.getParameter("room");

        List<Map<String, String>> availableRooms = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASS);

            String sql =
              "SELECT * FROM rooms r " +
              "WHERE r.location LIKE ? " +
              "AND r.capacity >= ? " +
              "AND r.id NOT IN (" +
              " SELECT room_id FROM bookings " +
              " WHERE NOT (check_out <= ? OR check_in >= ?)" +
              ")";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + location + "%");
            ps.setInt(2, Integer.parseInt(adults));
            ps.setString(3, checkIn);
            ps.setString(4, checkOut);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> room = new HashMap<>();
                room.put("name", rs.getString("name"));
                room.put("price", rs.getString("price"));
                room.put("type", rs.getString("type"));
                availableRooms.add(room);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("availableRooms", availableRooms);
        request.getRequestDispatcher("/jsp/user/rooms.jsp")
               .forward(request, response);
    }
}
