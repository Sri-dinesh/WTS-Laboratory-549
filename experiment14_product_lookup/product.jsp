<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Product Details</title>
  </head>
  <body>
    <h1>Product Details</h1>
    <dl>
      <dt>Product ID</dt>
      <dd><%= session.getAttribute("product_id") %></dd>
      <dt>Product Name</dt>
      <dd><%= session.getAttribute("product_name") %></dd>
      <dt>Description</dt>
      <dd><%= session.getAttribute("product_description") %></dd>
      <dt>Price</dt>
      <dd>$<%= session.getAttribute("price") %></dd>
    </dl>
    <p><a href="productSearch.jsp">Back to Search</a></p>
  </body>
</html>
