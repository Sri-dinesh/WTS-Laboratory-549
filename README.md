# Web Technologies Laboratory

This repository contains the Web Technologies laboratory exercises, JDBC examples, servlet applications, JSP programs, and the GreenSwap Hub mini-project.

## Project Map

| Folder                          | Main topic                         | How to run                                                   |
| ------------------------------- | ---------------------------------- | ------------------------------------------------------------ |
| `week1_mobile_store`            | Basic HTML pages                   | Open an HTML file in a browser                               |
| `week2_frames_css`              | Frames and CSS                     | Open `home.html` in a browser                                |
| `week3_registration_form`       | HTML registration form             | Open `index.html` in a browser                               |
| `week4_emi_calculator`          | HTML/JavaScript EMI calculator     | Open `index.html` in a browser                               |
| `week5_form_validation`         | Client-side form validation        | Open `index.html` in a browser                               |
| `week6_xml_dtd_schema`          | XML, DTD, and XSD                  | Open `book.xml` and inspect with an XML-aware editor/browser |
| `week7_xml_xslt`                | XML and XSLT transformation        | Open `booksdemo.xml` in a browser or XML editor              |
| `week8_jdbc_statement`          | JDBC CRUD with `Statement`         | Compile and run from a terminal                              |
| `week9_jdbc_preparedstatement`  | JDBC CRUD with `PreparedStatement` | Compile and run from a terminal                              |
| `week10_servlet_login`          | Servlet login validation           | Deploy to Tomcat                                             |
| `experiment11_employee_crud`    | Employee CRUD servlet              | Deploy to Tomcat and MariaDB/MySQL                           |
| `experiment12_state_management` | Sessions and cookies               | Deploy to Tomcat                                             |
| `experiment13_prime_sum`        | JSP prime-number sum               | Deploy to Tomcat                                             |
| `experiment14_product_lookup`   | JSP/JDBC product lookup            | Deploy to Tomcat and MariaDB/MySQL                           |
| `mini_project_greenswap_hub`    | JDBC + Servlets + JSP CRUD app     | Deploy to Tomcat and MariaDB/MySQL                           |
| `jdbc-connection`               | Console JDBC registration app      | Run `run.sh` or `run.bat`                                    |

## Requirements

Install the following before running the database-backed projects:

- JDK 8 or newer, with `java` and `javac` available on `PATH`
- Apache Tomcat 9 or compatible Servlet/JSP container
- MariaDB Server or MySQL Server
- A matching JDBC driver JAR
- A terminal, browser, and optionally Postman for HTTP testing

Set `JAVA_HOME` to the JDK installation directory. Set `CATALINA_HOME` to the Tomcat installation directory when using command-line deployment.

## Database Configuration

The repository's active Linux configuration uses MariaDB:

```text
URL:      jdbc:mariadb://localhost:3306/jdbc_demo
User:     eremika
Password: Mikasa
Driver:   org.mariadb.jdbc.Driver
```

The Windows/MySQL alternative is:

```text
URL:      jdbc:mysql://localhost:3306/jdbc_demo
User:     root
Password: your MySQL root password
Driver:   com.mysql.cj.jdbc.Driver
```

The Java and XML files contain commented MySQL alternatives. Switch the URL, username, password, driver class, and driver JAR as one complete set. Do not combine a MariaDB URL with the MySQL driver or vice versa.

Create the database used by the project before running its code:

- Weeks 8-10: use the `jdbc_demo` database and run each week's table-creation program as documented below. GreenSwap: run `mini_project_greenswap_hub/db/schema.sql` for its `swap_items` table.
- Experiment 11: run `experiment11_employee_crud/db/schema.sql` for `employee2`.
- Experiment 14: run `experiment14_product_lookup/db/schema.sql` for `product_catalog`.

For MariaDB, download the MariaDB Java Client. For MySQL, download MySQL Connector/J. Do not commit real production passwords; the classroom credentials in these examples are for local lab use.

## Weeks 1-7: Browser Labs

These projects do not need Java, Tomcat, or a database. Open the listed entry file directly in a browser, or serve the repository with any simple static file server:

```bash
python3 -m http.server 8000
```

Then open `http://localhost:8000/` and select a project from the repository page, or open the project HTML file directly.

Useful entry files:

- Week 1: `week1_mobile_store/index.html`
- Week 2: `week2_frames_css/home.html`
- Week 3: `week3_registration_form/index.html`
- Week 4: `week4_emi_calculator/index.html`
- Week 5: `week5_form_validation/index.html`
- Week 6: `week6_xml_dtd_schema/book.xml`
- Week 7: `week7_xml_xslt/booksdemo.xml`

## Weeks 8-9: Console JDBC Programs

Set a driver variable first. On Linux:

```bash
export JDBC_JAR="$HOME/.dbvis/drivers/maven/org/mariadb/jdbc/mariadb-java-client/3.5.9/mariadb-java-client-3.5.9.jar"
```

On Windows PowerShell:

```powershell
$env:JDBC_JAR = "C:\path\to\mariadb-java-client-3.5.9.jar"
```

For MySQL, point the variable to `mysql-connector-j-<version>.jar` and uncomment the MySQL connection values in the Java file.

### Week 8: Statement

From `week8_jdbc_statement` on Linux/macOS:

```bash
mkdir -p out
javac -cp "$JDBC_JAR" -d out *.java
java -cp "$JDBC_JAR:out" CreateTable
java -cp "$JDBC_JAR:out" InsertTable
java -cp "$JDBC_JAR:out" Retrieve
java -cp "$JDBC_JAR:out" Update
java -cp "$JDBC_JAR:out" Delete
```

On Windows, use `;` instead of `:` in the classpath:

```bat
mkdir out
javac -cp "%JDBC_JAR%" -d out *.java
java -cp "%JDBC_JAR%;out" CreateTable
```

Run the other Week 8 classes in the same way. The programs use the `jdbc_demo` database and the `emp1` table.

### Week 9: PreparedStatement

From `week9_jdbc_preparedstatement` on Linux/macOS:

```bash
mkdir -p out
javac -cp "$JDBC_JAR" -d out *.java
java -cp "$JDBC_JAR:out" CreateTablePrepared
java -cp "$JDBC_JAR:out" InsertTablePrepared
java -cp "$JDBC_JAR:out" GetTablePrepared
java -cp "$JDBC_JAR:out" UpdateTablePrepared
java -cp "$JDBC_JAR:out" DeleteTablePrepared
```

On Windows, replace `:` with `;` in the classpath. The programs use the `jdbc_demo` database and the `products` table.

## JDBC Connection Demo

The `jdbc-connection` project includes ready-made scripts.

Linux/macOS:

```bash
cd jdbc-connection
chmod +x run.sh
./run.sh
```

Windows Command Prompt or PowerShell:

```bat
cd jdbc-connection
run.bat "C:\path\to\mysql-connector-j-<version>.jar"
```

The Windows script can also receive a folder containing the Connector/J JAR. The active Linux configuration uses MariaDB; the commented Windows configuration in `src/RegistrationApp.java` uses MySQL.

## Servlet and JSP Deployment

For each web project:

1. Create the database and tables if the project uses JDBC.
2. Copy the project folder into Tomcat's `webapps` directory.
3. Compile Java sources into the project's `WEB-INF/classes` directory.
4. Copy the matching JDBC driver into `WEB-INF/lib` for database-backed projects.
5. Start Tomcat.
6. Open the project URL in a browser.

### Linux/macOS compile pattern

Run from the project folder. Replace `PROJECT` and source paths as needed:

```bash
mkdir -p WEB-INF/classes
javac -cp "$CATALINA_HOME/lib/servlet-api.jar:$JDBC_JAR" \
  -d WEB-INF/classes src/*.java
```

For packages stored below `src`, use:

```bash
javac -cp "$CATALINA_HOME/lib/servlet-api.jar:$JDBC_JAR" \
  -d webapp/WEB-INF/classes $(find src -name '*.java')
```

On Windows, use `;` instead of `:` and PowerShell's source-file expansion:

```powershell
javac -cp "$env:CATALINA_HOME\lib\servlet-api.jar;$env:JDBC_JAR" `
  -d WEB-INF\classes (Get-ChildItem src -Filter *.java)
```

Tomcat can translate JSP files when the application is deployed. The Servlet API JAR should be used for compilation, but should not be copied into `WEB-INF/lib` if Tomcat already provides it.

### Week 10: Servlet Login

Compile `week10_servlet_login/src/*.java` into `week10_servlet_login/WEB-INF/classes`, copy the MariaDB or MySQL driver into `WEB-INF/lib`, and deploy the folder as `ServletRequest` or another context name.

Open:

```text
http://localhost:8080/ServletRequest/index.html
```

The login servlet posts to `/login` and redirects to the success or failure page. The validation query expects a `users` table with `email` and `password` columns in `jdbc_demo`.

### Experiment 11: Employee CRUD

Run `experiment11_employee_crud/db/schema.sql`, compile `src/DBConnection.java` and `src/EmployeeServlet.java` into `WEB-INF/classes`, add the matching JDBC JAR to `WEB-INF/lib`, and deploy the folder.

Open:

```text
http://localhost:8080/experiment11_employee_crud/
```

The form supports add, update, delete, and list operations. The list endpoint is `/employee?action=list`; write operations use POST forms.

### Experiment 12: Sessions and Cookies

Compile `src/StateManagementServlet.java` into `WEB-INF/classes` and deploy the folder. No database or JDBC driver is required.

Open:

```text
http://localhost:8080/experiment12_state_management/state
```

Refresh the page to see the session visit count and persistent cookie count.

### Experiment 13: Prime Sum JSP

Deploy the folder without a database. Open:

```text
http://localhost:8080/experiment13_prime_sum/prime.jsp
```

Enter two whole numbers. The page sums primes strictly between the two values, regardless of their order.

### Experiment 14: Product Lookup

Run `experiment14_product_lookup/db/schema.sql`, compile `src/com/example/product/ProductDatabase.java` into `WEB-INF/classes`, copy the matching JDBC JAR into `WEB-INF/lib`, and deploy the folder.

Open:

```text
http://localhost:8080/experiment14_product_lookup/productSearch.jsp
```

A matching product redirects to `product.jsp`; an unknown product redirects to `invalidproduct.jsp`.

## GreenSwap Hub Mini-Project

GreenSwap is a JDBC + Servlet + JSP CRUD application for sharing reusable campus items.

### Build and deploy on Linux/macOS

From `mini_project_greenswap_hub`:

```bash
mkdir -p webapp/WEB-INF/classes webapp/WEB-INF/lib
export JDBC_JAR="$HOME/.dbvis/drivers/maven/org/mariadb/jdbc/mariadb-java-client/3.5.9/mariadb-java-client-3.5.9.jar"
javac -cp "$CATALINA_HOME/lib/servlet-api.jar:$JDBC_JAR" \
  -d webapp/WEB-INF/classes $(find src -name '*.java')
cp "$JDBC_JAR" webapp/WEB-INF/lib/
```

Copy the `webapp` directory to Tomcat's `webapps/greenswap` directory, or rename it to `greenswap` while copying. Then open:

```text
http://localhost:8080/greenswap/
```

Main routes:

- `/` or `/index.jsp` - home page
- `/add-item.jsp` - add a listing
- `/list-items` - view listings
- `/claim-item` - mark an item as claimed
- `/delete-item` - delete a listing

For Windows, use the MySQL Connector/J path in `JDBC_JAR`, use `;` in the `javac -cp` value, and switch the four connection values in `src/com/greenswap/util/DBConfig.java` or `webapp/WEB-INF/web.xml` as a complete set.

## Testing HTTP Endpoints

Use a browser for normal form flows or Postman for direct requests. Examples:

```text
GET  http://localhost:8080/experiment11_employee_crud/employee?action=list
GET  http://localhost:8080/experiment12_state_management/state
GET  http://localhost:8080/experiment14_product_lookup/productSearch.jsp?productQuery=Notebook
```

Use POST for add, update, delete, claim, and login form submissions. Avoid putting real passwords in URLs or source files.

## Troubleshooting

- `No suitable driver found`: verify the JDBC JAR, URL scheme, and driver class are from the same database vendor.
- `ClassNotFoundException`: check the JAR is on the compile/runtime classpath or inside `WEB-INF/lib`.
- `404 Not Found`: confirm the deployed context folder name and Tomcat URL.
- `javac is not recognized`: install a JDK and add its `bin` directory to `PATH`.
- Database access denied: verify the server is running and the configured user, password, database name, and port.
- JSP compilation errors: inspect Tomcat logs and confirm the Java classes are in `WEB-INF/classes` with their package directories preserved.

## Repository Notes

- The active examples use the classic Servlet API package `javax.servlet`, suitable for Tomcat 9.
- The source includes classroom credentials for local practice. Replace them with environment-specific values for real deployments.
- Each project README contains more focused details for that project.
