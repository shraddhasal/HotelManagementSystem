package com.hms.controller;

import com.hms.dao.BookingDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/booking/update")
public class UpdateBookingStatusServlet extends HttpServlet {

    private final BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminName") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/admin/login.jsp");
            return;
        }

        long bookingId = Long.parseLong(request.getParameter("id"));
        String status = request.getParameter("status");

        bookingDAO.updateBookingStatus(bookingId, status);

        response.sendRedirect(request.getContextPath() + "/admin/bookings");
    }
}