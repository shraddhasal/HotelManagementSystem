<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <style>
        body { font-family: Poppins, Arial; background:#f5f0ea; }
        .box {
            width: 400px;
            margin: 100px auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
        }
        input, button {
            width: 100%;
            margin-top: 12px;
            padding: 10px;
        }
        button {
            background: #8b5e3c;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="box">
    <h2>Admin Login</h2>
    <form action="<%=request.getContextPath()%>/admin/login" method="post">
        <input type="email" name="email" placeholder="Admin Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Login</button>
    </form>
</div>

</body>
</html>