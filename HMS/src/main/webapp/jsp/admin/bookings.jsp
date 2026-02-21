<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.hms.model.Booking" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bookings | Admin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0 8px 20px rgba(0,0,0,0.08);
        }
        th, td {
            padding: 14px;
            border-bottom: 1px solid #eee;
            text-align: left;
            font-size: 14px;
        }
        th {
            background: #f4f6f9;
        }
        .btn-confirm {
            background: #2ecc71;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 6px;
            cursor: pointer;
        }
        .btn-cancel {
            background: #e74c3c;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 6px;
            cursor: pointer;
        }
        .status {
            font-weight: 600;
        }
    </style>
</head>
<body>

<div class="dashboard">
    <h1>All Bookings</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Room</th>
            <th>Type</th>
            <th>Price</th>
            <th>Guests</th>
            <th>Check-In</th>
            <th>Check-Out</th>
            <th>Status</th>
            <th>Action</th>
        </tr>

        <%
            List<Booking> list =
                (List<Booking>) request.getAttribute("bookings");

            if (list != null) {
                for (Booking b : list) {
        %>
        <tr>
            <td><%= b.getId() %></td>
            <td><%= b.getUserName() %></td>
            <td><%= b.getRoomName() %></td>
            <td><%= b.getRoomType() %></td>
            <td>₹ <%= b.getPrice() %></td>
            <td>
                Adults: <%= b.getAdults() %><br>
                Child: <%= b.getChild() %>
            </td>
            <td><%= b.getCheckIn() %></td>
            <td><%= b.getCheckOut() %></td>
            <td class="status"><%= b.getStatus() %></td>
            <td>
                <% if ("PENDING".equalsIgnoreCase(b.getStatus())) { %>

                    <form action="<%=request.getContextPath()%>/admin/booking/update"
                          method="post" style="display:inline">
                        <input type="hidden" name="id" value="<%= b.getId() %>">
                        <input type="hidden" name="status" value="CONFIRMED">
                        <button type="submit" class="btn-confirm">Confirm</button>
                    </form>

                    <form action="<%=request.getContextPath()%>/admin/booking/update"
                          method="post" style="display:inline">
                        <input type="hidden" name="id" value="<%= b.getId() %>">
                        <input type="hidden" name="status" value="CANCELLED">
                        <button type="submit" class="btn-cancel">Cancel</button>
                    </form>

                <% } else { %>
                    —
                <% } %>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>

</body>
</html>