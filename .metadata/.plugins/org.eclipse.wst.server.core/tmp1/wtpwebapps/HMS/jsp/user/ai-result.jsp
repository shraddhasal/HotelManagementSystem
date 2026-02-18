<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>AI Room Recommendation</title>
    <style>
        body {
            font-family: Poppins, sans-serif;
            background: #f5f0ea;
            padding: 80px;
        }
        .card {
            background: white;
            padding: 30px;
            border-radius: 12px;
            max-width: 600px;
            margin: auto;
        }
    </style>
</head>
<body>

<div class="card">
    <h2>AI Recommendation</h2>

    <p><b>Your request:</b></p>
    <p><%= request.getParameter("query") %></p>

    <hr>

    <p><b>Suggested Room:</b></p>
    <ul>
        <li>Deluxe Family Room</li>
        <li>₹5,800 per night</li>
        <li>Free Breakfast</li>
    </ul>
</div>

</body>
</html>
