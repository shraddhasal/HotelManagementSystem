<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String userName = (String) session.getAttribute("userName");
%>

<nav class="navbar">

    <!-- LOGO -->
    <h2 class="logo"
        onclick="window.location.href='<%=request.getContextPath()%>/home.jsp'">
        AtithiStay
    </h2>

    <!-- MENU -->
    <ul>
        <li onclick="window.location.href='<%=request.getContextPath()%>/home.jsp'">
            Home
        </li>

        <li onclick="window.location.href='<%=request.getContextPath()%>/home.jsp#about'">
            About
        </li>

        <li onclick="window.location.href='<%=request.getContextPath()%>/jsp/user/rooms.jsp'">
            Our Rooms
        </li>

        <li onclick="window.location.href='<%=request.getContextPath()%>/home.jsp#facilities'">
            Facilities
        </li>

        <li onclick="window.location.href='<%=request.getContextPath()%>/home.jsp#gallery'">
            Gallery
        </li>

        <li onclick="window.location.href='<%=request.getContextPath()%>/home.jsp#contact'">
            Contact
        </li>

        <%-- SHOW ONLY WHEN LOGGED IN --%>
        <% if (userName != null) { %>
            <li onclick="window.location.href='<%=request.getContextPath()%>/jsp/user/mybookings.jsp'">
                My Bookings
            </li>
        <% } %>
    </ul>

    <!-- RIGHT SIDE -->
    <div class="nav-right">
        <% if (userName != null) { %>
            <span class="welcome-text">
                Hi, <%= userName %>
            </span>

            <a href="<%=request.getContextPath()%>/logout"
               class="nav-login-btn">
                Logout
            </a>
        <% } else { %>
            <a href="<%=request.getContextPath()%>/jsp/user/login.jsp"
               class="nav-login-btn">
                Login
            </a>
        <% } %>
    </div>

</nav>
