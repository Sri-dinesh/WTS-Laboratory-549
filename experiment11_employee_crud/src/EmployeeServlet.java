import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!"list".equals(request.getParameter("action"))
                && request.getParameter("action") != null) {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                    "Use POST for add, update, and delete operations.");
            return;
        }

        try (Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()) {
            listEmployees(statement, response);
        } catch (SQLException exception) {
            sendError(response, exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try (Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()) {
            int changedRows;
            if ("add".equals(action)) {
                changedRows = addEmployee(request, statement);
            } else if ("update".equals(action)) {
                changedRows = updateEmployee(request, statement);
            } else if ("delete".equals(action)) {
                changedRows = deleteEmployee(request, statement);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action.");
                return;
            }
            response.sendRedirect("employee?action=list&changed=" + changedRows);
        } catch (IllegalArgumentException exception) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
        } catch (SQLException exception) {
            sendError(response, exception);
        }
    }

    private int addEmployee(HttpServletRequest request, Statement statement)
            throws SQLException {
        String name = sqlText(required(request, "name"));
        String department = sqlText(required(request, "department"));
        double salary = validSalary(request.getParameter("salary"));
        return statement.executeUpdate("INSERT INTO Employee (name, department, salary) VALUES ('"
                + name + "', '" + department + "', " + salary + ")");
    }

    private int updateEmployee(HttpServletRequest request, Statement statement)
            throws SQLException {
        int id = positiveInteger(request.getParameter("id"), "id");
        String name = sqlText(required(request, "name"));
        String department = sqlText(required(request, "department"));
        double salary = validSalary(request.getParameter("salary"));
        return statement.executeUpdate("UPDATE Employee SET name='" + name + "', department='"
                + department + "', salary=" + salary + " WHERE id=" + id);
    }

    private int deleteEmployee(HttpServletRequest request, Statement statement)
            throws SQLException {
        int id = positiveInteger(request.getParameter("id"), "id");
        return statement.executeUpdate("DELETE FROM Employee WHERE id=" + id);
    }

    private void listEmployees(Statement statement, HttpServletResponse response)
            throws SQLException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (ResultSet results = statement.executeQuery("SELECT id, name, department, salary "
                + "FROM Employee ORDER BY id")) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'>"
                    + "<title>Employee List</title></head><body><h1>Employee List</h1>");
            out.println("<p><a href='index.html'>Back to forms</a></p><table border='1'>"
                    + "<tr><th>ID</th><th>Name</th><th>Department</th><th>Salary</th></tr>");
            while (results.next()) {
                out.printf("<tr><td>%d</td><td>%s</td><td>%s</td><td>%.2f</td></tr>%n",
                        results.getInt("id"), escape(results.getString("name")),
                        escape(results.getString("department")), results.getDouble("salary"));
            }
            out.println("</table></body></html>");
        }
    }

    private static String required(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " is required.");
        }
        return value.trim();
    }

    private static int positiveInteger(String value, String name) {
        try {
            int parsed = Integer.parseInt(value);
            if (parsed > 0)
                return parsed;
        } catch (NumberFormatException ignored) {
            // Convert malformed input into a client error below.
        }
        throw new IllegalArgumentException(name + " must be a positive integer.");
    }

    private static double validSalary(String value) {
        try {
            double salary = Double.parseDouble(value);
            if (Double.isFinite(salary) && salary >= 0)
                return salary;
        } catch (NumberFormatException ignored) {
            // Convert malformed input into a client error below.
        }
        throw new IllegalArgumentException("salary must be a non-negative number.");
    }

    private static String sqlText(String value) {
        return value.replace("'", "''");
    }

    private static String escape(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    private static void sendError(HttpServletResponse response, SQLException exception)
            throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Database error: " + exception.getMessage());
    }
}