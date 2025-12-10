<%@page language="java" import = "java.util.*, com.nsgacademy.crudapp.model.Student"%>
<html>
    <head>
        <title>MVC CRUD APPLICATION</title>
    </head>

    <body>
        <h2>MVC CRUD APPLICATION</h2>
        <h5>SERVLET + JSP + JDBC</h5>

        <%
            String success = (String)request.getParameter("success");
            if(success != null){
        %>
            <p style='color:green;'><%=success%></p>
        <%
            }
        %>

        <a href="students?action=add">Add Student</a>
        <table border="1" cellpadding="8">
            <thead>
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
                                <a href="students?action=edit&id=<%= s.getId() %>">Update</a>
                                <a href="students?action=delete&id=<%= s.getId() %>" onclick="return confirm('Are you sure to delete?')">Delete</a>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="5">No Students Found</t>
                    </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </body>
</html>