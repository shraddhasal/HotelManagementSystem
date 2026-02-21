<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>

<div class="topbar">
    <div class="logo">AtithiStay Admin</div>
    <div class="admin-info">Admin</div>
</div>

<div class="dashboard">

    <h1>Admin Dashboard</h1>

    <!-- STATS -->
    <div class="stats-grid">
        <div class="stat-card">
            <p>Total Bookings</p>
            <h2>${totalBookings}</h2>
        </div>

        <div class="stat-card">
            <p>Active Rooms</p>
            <h2>${activeRooms}</h2>
        </div>

        <div class="stat-card">
            <p>Registered Users</p>
            <h2>${totalUsers}</h2>
        </div>

        <div class="stat-card highlight">
            <p>Pending Requests</p>
            <h2>${pendingRequests}</h2>
        </div>
    </div>

    <!-- QUICK ACTIONS -->
    <h3 class="section-title">Quick Actions</h3>

    <div class="actions-grid">
        <a href="<%=request.getContextPath()%>/admin/bookings" class="action-card">
            <span class="action-icon">📋</span>
            <span class="action-text">View Bookings</span>
        </a>

        <a href="<%=request.getContextPath()%>/admin/rooms" class="action-card">
            <span class="action-icon">🏨</span>
            <span class="action-text">Manage Rooms</span>
        </a>

        <a href="<%=request.getContextPath()%>/admin/users" class="action-card">
            <span class="action-icon">👥</span>
            <span class="action-text">View Users</span>
        </a>

        <a href="<%=request.getContextPath()%>/admin/settings" class="action-card">
            <span class="action-icon">⚙️</span>
            <span class="action-text">Settings</span>
        </a>
    </div>

</div>
</body>
</html>