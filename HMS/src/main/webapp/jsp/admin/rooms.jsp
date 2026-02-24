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

    <!-- ===== ROOM FORM ===== -->
    <div class="room-form">
        <form action="<%=request.getContextPath()%>/admin/room/save"
              method="post" enctype="multipart/form-data">

            <!-- Hidden ID for EDIT -->
            <input type="hidden" name="id" id="roomId">

            <input type="text" name="roomName" id="roomName"
                   placeholder="Room Name" required>

            <select name="roomType" id="roomType" required>
                <option value="">Select Room Type</option>
                <option>Single</option>
                <option>Double</option>
                <option>Deluxe</option>
                <option>Suite</option>
                <option>Family</option>
                <option>Superior</option>
            </select>

            <input type="text" name="location" id="location"
                   placeholder="Location" required>

            <input type="number" name="price" id="price"
                   placeholder="Price" required>

            <input type="number" name="capacity" id="capacity"
                   placeholder="Capacity" required>

            <input type="number" name="totalRooms" id="totalRooms"
                   placeholder="Total Rooms" required>

            <input type="file" name="image" accept="image/*">

            <button type="submit">Save Room</button>
        </form>
    </div>

    <!-- ===== ROOMS TABLE ===== -->
    <table class="rooms-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Image</th>
            <th>Name</th>
            <th>Type</th>
            <th>Location</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<Room> list = (List<Room>) request.getAttribute("rooms");
            if (list != null && !list.isEmpty()) {
                for (Room r : list) {
        %>
        <tr onclick="editRoom(
                '<%= r.getId() %>',
                '<%= r.getRoomName() %>',
                '<%= r.getRoomType() %>',
                '<%= r.getLocation() %>',
                '<%= r.getPrice() %>',
                '<%= r.getCapacity() %>',
                '<%= r.getTotalRooms() %>'
        )">

            <td><%= r.getId() %></td>

            <td>
                <%
                    String img = r.getImage();
                    if (img != null && !img.trim().isEmpty()) {
                %>
                <img src="<%=request.getContextPath()%>/image/<%= img %>"
                     style="width:80px;height:55px;object-fit:cover;border-radius:6px;">
                <%
                    } else {
                %>
                —
                <%
                    }
                %>
            </td>

            <td><%= r.getRoomName() %></td>
            <td><%= r.getRoomType() %></td>
            <td><%= r.getLocation() %></td>
            <td>&#8377; <%= r.getPrice() %></td>

            <td>
                <a class="delete-btn"
                   href="<%=request.getContextPath()%>/admin/room/delete?id=<%= r.getId() %>"
                   onclick="event.stopPropagation(); return confirm('Delete this room?');">
                    Delete
                </a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="7" style="text-align:center;">No rooms found</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<!-- ===== EDIT PREFILL SCRIPT ===== -->
<script>
    function editRoom(id, name, type, location, price, capacity, totalRooms) {
        document.getElementById("roomId").value = id;
        document.getElementById("roomName").value = name;
        document.getElementById("roomType").value = type;
        document.getElementById("location").value = location;
        document.getElementById("price").value = price;
        document.getElementById("capacity").value = capacity;
        document.getElementById("totalRooms").value = totalRooms;

        window.scrollTo({top: 0, behavior: "smooth"});
    }
</script>

</body>
</html>