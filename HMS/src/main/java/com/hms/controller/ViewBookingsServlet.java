package com.hms.controller;

import com.hms.dao.BookingDAO;
import com.hms.model.Booking;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/bookings")
public class ViewBookingsServlet extends HttpServlet {

    private final BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminName") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/admin/login.jsp");
            return;
        }

        List<Booking> bookings = bookingDAO.getAllBookings();
        request.setAttribute("bookings", bookings);

        try {
            request.getRequestDispatcher("/jsp/admin/bookings.jsp")
                   .forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}