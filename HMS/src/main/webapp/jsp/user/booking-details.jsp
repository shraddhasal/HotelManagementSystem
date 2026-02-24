<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String roomId = request.getParameter("roomId");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Booking Details | AtithiStay</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/navbar.css">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: #f5f5f5;
            margin: 0;
        }

        .booking-container {
            max-width: 900px;
            margin: 120px auto 60px;
            background: #fff;
            padding: 40px;
            border-radius: 18px;
            box-shadow: 0 12px 28px rgba(0,0,0,0.08);
        }

        h1 {
            margin-bottom: 10px;
        }

        .subtitle {
            color: #777;
            margin-bottom: 30px;
        }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 22px;
        }

        label {
            font-weight: 500;
            margin-bottom: 6px;
            display: block;
        }

        input {
            width: 100%;
            padding: 12px 14px;
            border-radius: 10px;
            border: 1px solid #ddd;
            font-size: 14px;
        }

        .full {
            grid-column: 1 / -1;
        }

        .confirm-btn {
            margin-top: 30px;
            background: #a06b3c;
            color: #fff;
            border: none;
            padding: 14px;
            width: 100%;
            border-radius: 28px;
            font-size: 16px;
            cursor: pointer;
        }

        .confirm-btn:hover {
            background: #8b5a2b;
        }
    </style>
</head>

<body>

<jsp:include page="/jsp/common/navbar.jsp" />

<div class="booking-container">

    <h1>Confirm Your Booking</h1>
    <p class="subtitle">Fill in your stay details and confirm your reservation</p>

    <form action="<%=request.getContextPath()%>/user/confirm-booking" method="post">

        <input type="hidden" name="roomId" value="<%= roomId %>">

        <div class="form-grid">

            <div>
                <label>Check-in Date</label>
                <input type="date" name="checkIn" required>
            </div>

            <div>
                <label>Check-out Date</label>
                <input type="date" name="checkOut" required>
            </div>

            <div>
                <label>Adults</label>
                <input type="number" name="adults" min="1" value="1" required>
            </div>

            <div>
                <label>Children</label>
                <input type="number" name="child" min="0" value="0">
            </div>

        </div>

        <button type="submit" class="confirm-btn">
            Confirm Booking
        </button>

    </form>

</div>

</body>
</html>