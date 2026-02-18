package com.hms.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("EMAIL = " + email);
        System.out.println("PASSWORD = " + password);

        try (Connection con = com.hms.util.DBConnection.getConnection()) {

            String sql = "SELECT id, full_name FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("userName", rs.getString("full_name"));

                response.sendRedirect(
                    request.getContextPath() + "/home.jsp"
                );
            } else {
                response.sendRedirect(
                    request.getContextPath() + "/jsp/user/login.jsp?error=true"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(
                request.getContextPath() + "/jsp/user/login.jsp?error=true"
            );
        }
    }
}
