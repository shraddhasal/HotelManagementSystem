<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, com.hms.model.User" %>

<%
    List<User> users = (List<User>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Users | Admin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

<!-- TOP BAR -->
<div class="topbar">
    <div class="logo">AtithiStay Admin</div>
    <div class="admin-info">Admin</div>
</div>

<!-- DASHBOARD -->
<div class="dashboard">

    <h2>Registered Users</h2>

    <table class="users-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Registered On</th>
            </tr>
        </thead>

        <tbody>
        <% if (users != null && !users.isEmpty()) {
            for (User u : users) { %>
            <tr>
                <td><%= u.getId() %></td>
                <td><%= u.getFullName() %></td>
                <td><%= u.getEmail() %></td>
                <td><%= u.getMobile() != null ? u.getMobile() : "-" %></td>
                <td><%= u.getCreatedAt() %></td>
            </tr>
        <% } } else { %>
            <tr>
                <td colspan="5">No users found</td>
            </tr>
        <% } %>
        </tbody>
    </table>

</div>

</body>
</html>