# Experiment 11: Employee CRUD Servlet

This Tomcat application performs list, add, update, and delete operations on the `Employee` table using JDBC `Statement`.

## Setup

1. Run `db/schema.sql` in MariaDB.
2. Copy the MariaDB Java Client JAR into `WEB-INF/lib`.
3. Compile `src/DBConnection.java` and `src/EmployeeServlet.java` into `WEB-INF/classes` with the Servlet API JAR on the classpath.
4. Copy this folder to Tomcat's `webapps` directory.
5. Open `http://localhost:8080/experiment11_employee_crud/`.

The connection defaults match the earlier JDBC experiments: URL `jdbc:mariadb://localhost:3306/employee2`, user `eremika`, password `Mikasa`, and driver `org.mariadb.jdbc.Driver`. Override them with the JVM properties `employee.db.url`, `employee.db.user`, and `employee.db.password`.

For Windows with MySQL, comment the MariaDB URL and driver in `src/DBConnection.java` and use `jdbc:mysql://localhost:3306/employee2`, user `root`, your MySQL root password, and `com.mysql.cj.jdbc.Driver`. Add MySQL Connector/J to `WEB-INF/lib`.

Although this experiment demonstrates `Statement`, input is validated and text values are escaped before SQL execution. `PreparedStatement` is preferred in production applications.
