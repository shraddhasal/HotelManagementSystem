package com.hms.controller;

import com.hms.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

@WebServlet("/user/confirm-booking")
public class ConfirmBookingServlet extends HttpServlet {

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
        Date checkIn = Date.valueOf(request.getParameter("checkIn"));
        Date checkOut = Date.valueOf(request.getParameter("checkOut"));
        int adults = Integer.parseInt(request.getParameter("adults"));
        int child = Integer.parseInt(request.getParameter("child"));

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

        response.sendRedirect(
            request.getContextPath() + "/user/my-bookings?success=true"
        );
    }
}