package com.hms.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

import com.hms.util.DBConnection;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminName") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/admin/login.jsp");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            request.setAttribute("totalBookings", count(con, "SELECT COUNT(*) FROM bookings"));
            request.setAttribute("activeRooms", count(con, "SELECT COUNT(*) FROM rooms"));
            request.setAttribute("totalUsers", count(con, "SELECT COUNT(*) FROM users"));
            request.setAttribute("pendingRequests", 0); // safe default

            request.getRequestDispatcher("/jsp/admin/dashboard.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Dashboard error: " + e.getMessage());
        }
    }

    private int count(Connection con, String sql) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}