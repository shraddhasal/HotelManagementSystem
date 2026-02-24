package com.hms.controller;

import com.hms.dao.BookingDAO;
import com.hms.model.Booking;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/my-bookings")
public class MyBookingsServlet extends HttpServlet {

    private final BookingDAO bookingDAO = new BookingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        List<Booking> bookings = bookingDAO.getBookingsByUser(userId);
        request.setAttribute("bookings", bookings);

        request.getRequestDispatcher("/jsp/user/mybookings.jsp")
               .forward(request, response);
    }
}