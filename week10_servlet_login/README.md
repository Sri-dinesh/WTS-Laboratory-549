# Week 10: Servlet Login Validation

## Objective

Write a servlet program to read username and password from a form and verify the fields at webserver. If the credentials are valid redirect to `success.html` page otherwise `failure.html`.

## Pre-Lab Questions

1. What is a Web server?
2. What is Client-Server Architecture?
3. List out the difference between Web Server and Application Server?
4. What is a Servlet?
5. Write down the Servlet Life Cycle?
6. How to read input parameters from a web application into a servlet?
7. List out the differences between Servlet Context and Servlet Config?
8. Write down the steps to connect a Web application to the database using servlet?
9. Does Servlet follow MVC paradigm? Justify
10. What is Tomcat?
11. Know the steps to install tomcat into your system.
12. What is Session Tracking?
13. What is the use of Session and Cookies?

## Files

- `index.html` - Login form
- `src/Login.java` - Servlet that reads form input and redirects based on validation result
- `src/Validate.java` - JDBC helper that checks credentials against the database
- `src/Welcome.java` - Sample servlet included in the original lab format
- `success.html` - Shown after valid credentials
- `failure.html` - Shown after invalid credentials
- `WEB-INF/web.xml` - Deployment descriptor

## Database Connection

Uses the same credentials as the earlier JDBC exercises:

- URL: `jdbc:mariadb://localhost:3306/jdbc_demo`
- User: `eremika`
- Password: `Mikasa`
- Driver: `org.mariadb.jdbc.Driver`

### Windows / MySQL alternative

`src/Validate.java` contains a commented MySQL alternative. Comment the MariaDB URL, user, password, and driver together, then use `jdbc:mysql://localhost:3306/jdbc_demo`, user `root`, your MySQL root password, and `com.mysql.cj.jdbc.Driver`. Add MySQL Connector/J to the application classpath.

## Expected Table

The validation query uses the `users` table from `jdbc_demo` with at least:

- `email`
- `password`

## Deployment Steps

1. Create an application folder named `ServletRequest`
2. Place `index.html`, `WEB-INF` folder, and `src` folder inside it
3. `WEB-INF` should contain `classes` folder and `web.xml`
4. Compile servlet code and place `.class` files in `WEB-INF/classes`
5. Copy the application folder into `C:\xampp\tomcat\webapps`
6. Start Tomcat from XAMPP control panel
7. Open `http://localhost:8080/ServletRequest/`
