<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Sign Up | AtithiStay</title>

    <!-- CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Signup.css">
</head>
<body>

<div class="signup-page">

    <!-- LEFT SIDE -->
    <div class="signup-left">
        <h1>Hello, Welcome!</h1>
        <p>
            Create your AtithiStay account to book luxury rooms,
            manage reservations, and enjoy premium hospitality.
        </p>

        <button class="view-btn">View More</button>
    </div>

    <!-- RIGHT SIDE -->
    <div class="signup-right">
        <div class="signup-card">
            <h2>Sign Up</h2>

            <!-- SIGNUP FORM -->
            <form action="<%=request.getContextPath()%>/signup" method="post">

                <input
                    type="text"
                    name="fullName"
                    placeholder="Full Name"
                    required
                />

                <input
                    type="email"
                    name="email"
                    placeholder="Email Address"
                    required
                />

                <input
                    type="text"
                    name="mobile"
                    placeholder="Mobile Number"
                    required
                />

                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    required
                />

                <input
                    type="password"
                    name="confirmPassword"
                    placeholder="Confirm Password"
                    required
                />

                <button type="submit" class="signup-btn">
                    Sign Up
                </button>
            </form>

            <p class="login-link">
                Already have an account?
                <a href="<%=request.getContextPath()%>/jsp/user/login.jsp">
                    Login
                </a>
            </p>

        </div>
    </div>

</div>

</body>
</html>
