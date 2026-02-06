<%@page import="com.nsgacademy.crudapp.model.User"%>

<%
    User user = (User) session.getAttribute("user");
%>

<% if(user != null){ %>

    <div class="d-flex justify-content-end align-items-center mb-3">

        <span class="me-3 text-muted">
            Welcome <strong><%= user.getUsername() %></strong>
        </span>

        <a href="auth?action=logout" class="btn btn-danger btn-sm">
            Logout
        </a>

    </div>

<% } %>
