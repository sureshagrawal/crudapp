<%@page isErrorPage="true"%>
<html>
    <head>
        <title>MVC CRUD APPLICATION</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
    </head>
    <body class="container my-4">
        <h2 class="text-danger">Something Wrong Happened!</h2>
        <h5 class="text-muted mb-3">Encountered some issue processing request</h5>

        <%
            String errorMessage = (String)request.getAttribute("errorMessage");
            Throwable errorCause = (Throwable)request.getAttribute("errorCause");
            exception = (Throwable)request.getAttribute("errorException");
        %>
        <div class="card">
            <div class="card-header bg-dark text-light">
                Debugging Information
            </div>
            <div class="card-body">
                <p><strong>Message</strong></p>
                <pre><%= (errorMessage !=null ? errorMessage : "")%></pre>

                <%
                    if(errorCause != null){
                %>
                        <p><strong>Root Cause</strong></p>
                        <pre><%= errorCause%></pre>
                <%
                    } else if(exception != null) {
                %>
                        <p><strong>Main Exception</strong></p>
                        <pre><%= exception%></pre>
                <%
                    } else {
                %>
                        <pre>No Technical Error Found</pre>
                <%
                    }
                %>

                <a href="students?action=list" class="btn btn-primary">Go Back</a>
            </div>
        </div>
    </body>
</html>