<%@page isErrorPage="true"%>
<html>
    <head>
        <title>MVC CRUD APPLICATION</title>
    </head>
    <body>
        <h1>Error Occured</h1>
        <%
            String errorMessage = (String)request.getAttribute("errorMessage");
            Throwable errorCause = (Throwable)request.getAttribute("errorCause");
            exception = (Throwable)request.getAttribute("errorException");
        %>

        <p><%= errorMessage%></p>
        <p><%= errorCause%></p>
        <p><%= exception%></p>

        <a href="students?action=list">Go Back</a>
    </body>
</html>