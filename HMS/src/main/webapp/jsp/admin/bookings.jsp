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
            vertical-align: middle;
        }

        th {
            background: #f4f6f9;
            font-weight: 600;
        }

        .btn-confirm {
            background: #2ecc71;
            color: #fff;
            border: none;
            padding: 6px 12px;
            border-radius: 6px;
            cursor: pointer;
        }

        .btn-cancel {
            background: #e74c3c;
            color: #fff;
            border: none;
            padding: 6px 12px;
            border-radius: 6px;
            cursor: pointer;
        }

        .btn-confirm:hover {
            background: #27ae60;
        }

        .btn-cancel:hover {
            background: #c0392b;
        }

        .status {
            font-weight: 600;
        }

        .status.PENDING {
            color: #f39c12;
        }

        .status.CONFIRMED {
            color: #27ae60;
        }

        .status.CANCELLED {
            color: #c0392b;
        }
    </style>
</head>

<body>

<div class="dashboard">
    <h1>All Bookings</h1>

    <table>
        <thead>
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
        </thead>

        <tbody>
        <%
            List<Booking> list = (List<Booking>) request.getAttribute("bookings");

            if (list != null && !list.isEmpty()) {
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

            <!-- STATUS -->
            <td class="status <%= b.getStatus() %>">
                <%= b.getStatus() %>
            </td>

            <!-- ACTION -->
            <td>
                <% if ("PENDING".equalsIgnoreCase(b.getStatus())) { %>

                    <form action="<%=request.getContextPath()%>/admin/booking/update"
                          method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= b.getId() %>">
                        <input type="hidden" name="status" value="APPROVED">
                        <button type="submit" class="btn-confirm">
                            Confirm
                        </button>
                    </form>

                    <form action="<%=request.getContextPath()%>/admin/booking/update"
                          method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= b.getId() %>">
                        <input type="hidden" name="status" value="CANCELLED">
                        <button type="submit" class="btn-cancel">
                            Cancel
                        </button>
                    </form>

                <% } else { %>
                    —
                <% } %>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="10" style="text-align:center; padding:20px;">
                No bookings found
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>