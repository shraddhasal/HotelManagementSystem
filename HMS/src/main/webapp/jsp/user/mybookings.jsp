<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, com.hms.model.Booking" %>

<%
    String userName = (String) session.getAttribute("userName");
    if (userName == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp");
        return;
    }

    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Bookings | AtithiStay</title>

    <!-- Navbar CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/navbar.css">

    <!-- My Bookings CSS (ONLY this, no home.css) -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mybookings.css">

    <!-- Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">
</head>

<body>

<!-- NAVBAR -->
<jsp:include page="/jsp/common/navbar.jsp" />

<div class="mybookings-page">

    <div class="mybookings-header">
        <h1>My Bookings</h1>
        <p>Manage your hotel reservations with ease</p>
    </div>

    <div class="bookings-container">

        <% if (bookings != null && !bookings.isEmpty()) {
            for (Booking b : bookings) { %>

        <!-- BOOKING CARD -->
        <div class="booking-card">

            <!-- LEFT -->
            <div class="booking-left">
                <h3><%= b.getRoomName() %></h3>
                <p class="location">Room Type: <%= b.getRoomType() %></p>
                <p>Check-in: <b><%= b.getCheckIn() %></b></p>
                <p>Check-out: <b><%= b.getCheckOut() %></b></p>
                <p>
                    Guests:
                    Adults <%= b.getAdults() %>,
                    Child <%= b.getChild() %>
                </p>
            </div>

            <!-- RIGHT -->
            <div class="booking-right">

                <!-- STATUS -->
                <span class="status <%= b.getStatus() %>">
                    <%= b.getStatus() %>
                </span>

                <!-- PRICE -->
                <div class="price">₹ <%= b.getPrice() %></div>

                <!-- CANCEL (ONLY IF PENDING) -->
                <% if ("PENDING".equalsIgnoreCase(b.getStatus())) { %>
                    <form action="<%=request.getContextPath()%>/user/cancel-booking" method="post">
                        <input type="hidden" name="bookingId" value="<%= b.getId() %>">
                        <button type="submit" class="cancel-btn">
                            Cancel Booking
                        </button>
                    </form>
                <% } %>

            </div>

        </div>

        <% } } else { %>

        <p style="padding:20px;">You have no bookings yet.</p>

        <% } %>

    </div>
</div>

</body>
</html>