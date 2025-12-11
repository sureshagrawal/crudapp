<%@page language="java" import="com.nsgacademy.crudapp.model.Student"%>
<html>
    <head>
        <title>MVC CRUD APPLICATION</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4">
        <h2 class="text-center mb-3">MVC CRUD APPLICATION</h2>
        <h5 class="text-center mb-3">SERVLET + JSP + JDBC</h5>

        <%
            Student student = (Student)request.getAttribute("student");
            boolean isEdit = (student != null);
        %>

        <h4 class="mb-4"><%= (isEdit ? "Edit" : "Add")%> Student</h4>

        <form action="students?action=<%= (isEdit ? "update" : "insert")%>" method="post">
            <%
                if(isEdit){
            %>
                <input type="hidden" name="id" value="<%= student.getId() %>">
            <%
                }
            %>

        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control"
                value="<%= (request.getParameter("name")!=null) ? request.getParameter("name") : (student!=null ? student.getName() : "") %>"
                placeholder="Enter Name"
                required
                pattern="[A-Za-z ]{3,50}"
                title="Name should be at least 3 to 50 characters"
                >

            <%String nameError = (String)request.getAttribute("nameError");%>
            <p class="text-danger"><%= (nameError != null ? nameError : "")%></p>
        </div>

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email" class="form-control"
                value="<%= (request.getParameter("email")!=null) ? request.getParameter("email") : (student!=null ? student.getEmail() : "") %>"
                placeholder="Enter Email"
                required
                >

            <%String emailError = (String)request.getAttribute("emailError");%>
            <p class="text-danger"><%= (emailError != null ? emailError : "")%></p>
        </div>

        <div class="mb-3">
            <label class="form-label">Mobile</label>
            <input type="text" name="mobile" class="form-control"
                value="<%= (request.getParameter("mobile")!=null) ? request.getParameter("mobile") : (student!=null ? student.getMobile() : "") %>"
                placeholder="Enter Mobile"
                required
                pattern="[0-9]{10}"
                title="Mobile Number should be of 10 digits"
                >

            <%String mobileError = (String)request.getAttribute("mobileError");%>
            <p class="text-danger"><%= (mobileError != null ? mobileError : "")%></p>
        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="students" class="btn btn-secondary">Cancel</a>
        </form>
    </body>
</html>