package com.hms.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/ai-recommend")
public class AIRecommendationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String query = request.getParameter("query").toLowerCase();

        String recommendedRoom = "Deluxe Room"; // default

        // 🔥 AI RULES
        if (query.contains("family")) {
            recommendedRoom = "Family Suite";
        } else if (query.contains("business")) {
            recommendedRoom = "Executive Room";
        } else if (query.contains("honeymoon") || query.contains("couple")) {
            recommendedRoom = "Premium Suite";
        } else if (query.contains("budget")) {
            recommendedRoom = "Deluxe Room";
        }

        request.setAttribute("recommendation", recommendedRoom);
        request.setAttribute("userQuery", query);

        request.getRequestDispatcher("/jsp/user/ai-result.jsp")
               .forward(request, response);
    }
}
