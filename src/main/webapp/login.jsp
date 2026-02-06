<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body{
            background: linear-gradient(135deg,#0d6efd,#6610f2);
            height:100vh;
        }
    </style>
</head>

<body class="d-flex justify-content-center align-items-center">

<div class="card shadow p-4" style="width:380px">

    <h3 class="text-center mb-4">🔐 Login</h3>

    <%
        String error = (String)request.getAttribute("error");
        String success = request.getParameter("success");
    %>

    <% if(success != null){ %>
        <div class="alert alert-success text-center"><%=success%></div>
    <% } %>

    <% if(error != null){ %>
        <div class="alert alert-danger text-center"><%=error%></div>
    <% } %>

    <!-- 🔥 IMPORTANT: doLogin -->
    <form action="auth?action=doLogin" method="post">

        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" name="username" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <button class="btn btn-primary w-100">Login</button>
    </form>

    <div class="text-center mt-3">
        Don't have account?
        <a href="auth?action=register">Register</a>
    </div>

</div>

</body>
</html>
