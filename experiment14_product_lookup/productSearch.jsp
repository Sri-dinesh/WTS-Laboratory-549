<%@ page import="java.sql.Connection,java.sql.PreparedStatement,java.sql.ResultSet" %>
<%@ page import="com.example.product.ProductDatabase" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Lookup</title>
</head>
<body>
    <h1>Product Lookup</h1>
    <form method="get" action="productSearch.jsp">
        <label for="query">Product ID or name</label>
        <input id="query" name="productQuery" required maxlength="120">
        <button type="submit">Search</button>
    </form>
    <%
        String productQuery = request.getParameter("productQuery");
        if (productQuery != null && !productQuery.trim().isEmpty()) {
            String sql = "SELECT product_id, product_name, product_description, price "
                    + "FROM products WHERE CAST(product_id AS CHAR) = ? OR product_name = ?";
            try (Connection connection = ProductDatabase.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, productQuery.trim());
                statement.setString(2, productQuery.trim());
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        session.setAttribute("product_id", result.getInt("product_id"));
                        session.setAttribute("product_name", result.getString("product_name"));
                        session.setAttribute("product_description", result.getString("product_description"));
                        session.setAttribute("price", result.getBigDecimal("price"));
                        response.sendRedirect("product.jsp");
                        return;
                    }
                }
                response.sendRedirect("invalidproduct.jsp");
                return;
            } catch (Exception exception) {
    %>
                <p role="alert">Database error: <%= exception.getMessage() %></p>
    <%
            }
        }
    %>
</body>
</html>