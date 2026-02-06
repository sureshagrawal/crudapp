<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body{
            background: linear-gradient(135deg,#198754,#20c997);
            height:100vh;
        }
    </style>
</head>

<body class="d-flex justify-content-center align-items-center">

<div class="card shadow p-4" style="width:380px">

    <h3 class="text-center mb-4">📝 Register</h3>

    <%
        String error = (String)request.getAttribute("error");
    %>

    <% if(error != null){ %>
        <div class="alert alert-danger text-center"><%=error%></div>
    <% } %>

    <!-- 🔥 IMPORTANT: doRegister -->
    <form action="auth?action=doRegister" method="post">

        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Mobile</label>
            <input type="number" name="mobile" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" name="username" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <button class="btn btn-success w-100">Register</button>
    </form>

    <div class="text-center mt-3">
        Already have account?
        <a href="auth">Login</a>
    </div>

</div>

</body>
</html>
