<%@ page isErrorPage="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Error</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background: #fff;
        color: #111827;
        padding: 40px;
      }
      .card {
        max-width: 720px;
        margin: 0 auto;
        border: 1px solid #e5e7eb;
        border-radius: 10px;
        padding: 28px;
      }
      .error {
        color: #dc2626;
      }
      a {
        color: #2563eb;
        text-decoration: none;
        font-weight: 700;
      }
    </style>
  </head>
  <body>
    <div class="card">
      <h1 class="error">Something went wrong</h1>
      <p>
        <%= request.getAttribute("errorMessage") != null ?
        request.getAttribute("errorMessage") : "Please try again." %>
      </p>
      <p><a href="index.jsp">Return home</a></p>
    </div>
  </body>
</html>
