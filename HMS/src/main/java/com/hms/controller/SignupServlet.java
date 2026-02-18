package com.hms.controller;

import com.hms.util.DBConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("jsp/user/signup.jsp?error=password");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            String sql =
              "INSERT INTO users(full_name, email, mobile, password) VALUES (?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, password); // (later we hash)

            ps.executeUpdate();

            response.sendRedirect("jsp/user/login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/user/signup.jsp?error=exists");
        }
    }
}
