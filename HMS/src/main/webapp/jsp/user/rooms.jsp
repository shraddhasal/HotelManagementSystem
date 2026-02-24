<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, com.hms.model.Room" %>

<%
    List<Room> rooms = (List<Room>) request.getAttribute("availableRooms");
    String user = (String) session.getAttribute("userName");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Our Rooms</title>

    <!-- CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Rooms.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/FilterSidebar.css">
</head>

<body>

<div class="rooms-page">

    <!-- ===== PAGE HEADER ===== -->
    <section class="rooms-header">
        <h1>Our Rooms</h1>
        <p>
            Choose from our wide range of luxury rooms and suites designed for
            comfort, elegance, and relaxation.
        </p>
    </section>

    <!-- ===== MAIN LAYOUT ===== -->
    <div class="rooms-layout">

        <!-- FILTER SIDEBAR -->
        <%@ include file="filterSidebar.jsp" %>

        <!-- ===== ROOMS LIST ===== -->
        <section class="rooms-list">

            <% if (rooms != null && !rooms.isEmpty()) {
                for (Room r : rooms) { %>

            <div class="room-horizontal-card">

                <!-- IMAGE -->
                <div class="room-image">
                    <% if (r.getImage() != null) { %>
                        <img src="<%=request.getContextPath()%>/image/<%= r.getImage() %>" alt="Room">
                    <% } else { %>
                        <img src="<%=request.getContextPath()%>/images/no-image.png" alt="Room">
                    <% } %>
                </div>

                <!-- DETAILS -->
                <div class="room-details">
                    <div class="room-title"><%= r.getRoomName() %></div>
                    <div class="room-location"><%= r.getLocation() %></div>
                    <p>Type: <%= r.getRoomType() %></p>
                </div>

                <!-- PRICE / ACTION -->
                <div class="room-price-box">
                    <div class="price">
                        ₹<%= r.getPrice() %> / night
                    </div>

                    <% if (user == null) { %>
                        <a href="<%=request.getContextPath()%>/jsp/user/login.jsp"
                           class="book-now-btn">
                           Login to Book
                        </a>
                    <% } else { %>
                        <form action="<%=request.getContextPath()%>/user/booking-details" method="get">
    <input type="hidden" name="roomId" value="<%= r.getId() %>">
    <button type="submit" class="book-now-btn">
        Book Now
    </button>
</form>
                    <% } %>
                </div>

            </div>

            <% } } else { %>
                <p style="padding:20px;">No rooms available</p>
            <% } %>

        </section>

    </div>
</div>

</body>
</html>