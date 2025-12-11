<%@page language="java" import = "java.util.*, com.nsgacademy.crudapp.model.Student"%>
<html>
    <head>
        <title>MVC CRUD APPLICATION</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body class="container mt-4">
        <h2 class="text-center mb-3">MVC CRUD APPLICATION</h2>
        <h5 class="text-center mb-3">SERVLET + JSP + JDBC</h5>

        <%
            String success = (String)request.getParameter("success");
            if(success != null){
        %>
            <div class="alert alert-success text-center"><%=success%></div>
        <%
            }
        %>

        <a href="students?action=add" class="btn btn-primary mb-3">
            <i class="fa-solid fa-user-plus me-1"></i>Add Student
        </a>
        <table class="table table-bordered table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
            <%
                int cnt = 1;
                List<Student> students = (List<Student>)request.getAttribute("students");
                if(students != null && !students.isEmpty()){
                    for(Student s : students){
            %>
                        <tr>
                            <td><%= cnt++ %></td>
                            <td><%= s.getName() %></td>
                            <td><%= s.getEmail() %></td>
                            <td><%= s.getMobile() %></td>
                            <td>
                                <a href="students?action=edit&id=<%= s.getId() %>"
                                    class="btn btn-warning btn-sm me-1">
                                        <i class="fa-solid fa-pen-to-square"></i>
                                </a>

                                <a href="students?action=delete&id=<%= s.getId() %>" class="btn btn-danger btn-sm"
                                    onclick="return confirm('Are you sure to delete?')">
                                        <i class="fa-solid fa-trash"></i>
                                </a>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="5" class="text-center">No Students Found</t>
                    </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </body>
</html>