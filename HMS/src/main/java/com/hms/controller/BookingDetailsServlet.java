package com.hms.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/user/booking-details")
public class BookingDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String roomId = request.getParameter("roomId");
        response.sendRedirect(
            request.getContextPath() + "/jsp/user/booking-details.jsp?roomId=" + roomId
        );
    }
}