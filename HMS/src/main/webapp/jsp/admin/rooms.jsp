<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.hms.model.Room" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Rooms | Admin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

<div class="dashboard">
<h1>Manage Rooms</h1>

<div class="room-form">
<form action="<%=request.getContextPath()%>/admin/room/save"
      method="post" enctype="multipart/form-data">

    <input type="hidden" name="id" id="roomId">

    <input name="roomName" id="roomName" placeholder="Room Name" required>

    <select name="roomType" id="roomType" required>
        <option value="">Select Room Type</option>
        <option>Single</option>
        <option>Double</option>
        <option>Deluxe</option>
        <option>Suite</option>
        <option>Family</option>
        <option>Superior</option>
    </select>

    <input name="location" id="location" placeholder="Location">
    <input name="price" id="price" placeholder="Price" required>
    <input name="capacity" id="capacity" placeholder="Capacity" required>
    <input name="totalRooms" id="totalRooms" placeholder="Total Rooms" required>

    <input type="file" name="image" accept="image/*">

    <button type="submit">Save Room</button>
</form>
</div>

<table class="rooms-table">
<tr>
<th>ID</th>
<th>Image</th>
<th>Name</th>
<th>Type</th>
<th>Location</th>
<th>Price</th>
<th>Action</th>
</tr>

<%
List<Room> list = (List<Room>) request.getAttribute("rooms");
if (list != null) {
    for (Room r : list) {
%>
<tr>
<td><%= r.getId() %></td>
<td>
    <% if (r.getImage() != null) { %>
        <img src="<%=request.getContextPath()%>/image/<%= r.getImage() %>"
     style="width:80px;height:55px;object-fit:cover;border-radius:6px;">
    <% } else { %>
        —
    <% } %>
</td>
<td><%= r.getRoomName() %></td>
<td><%= r.getRoomType() %></td>
<td><%= r.getLocation() %></td>
<td>&#8377; <%= r.getPrice() %></td>
<td>
    <a class="delete-btn"
       href="<%=request.getContextPath()%>/admin/room/delete?id=<%= r.getId() %>"
       onclick="return confirm('Delete this room?')">Delete</a>
</td>
</tr>
<% }} %>
</table>

</div>
</body>
</html>