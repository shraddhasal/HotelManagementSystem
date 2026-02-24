package com.hms.controller;

import com.hms.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

@WebServlet("/user/book-room")
public class BookRoomServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        long roomId = Long.parseLong(request.getParameter("roomId"));

        // TEMP values (later you can use form inputs)
        Date checkIn = Date.valueOf("2026-02-25");
        Date checkOut = Date.valueOf("2026-02-27");
        int adults = 2;
        int child = 0;

        String sql = """
            INSERT INTO bookings
            (user_id, room_id, check_in, check_out, adults, child, status)
            VALUES (?, ?, ?, ?, ?, ?, 'PENDING')
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setLong(2, roomId);
            ps.setDate(3, checkIn);
            ps.setDate(4, checkOut);
            ps.setInt(5, adults);
            ps.setInt(6, child);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // SUCCESS → redirect to My Bookings
        response.sendRedirect(
            request.getContextPath() + "/user/my-bookings?success=true"
        );
    }
}