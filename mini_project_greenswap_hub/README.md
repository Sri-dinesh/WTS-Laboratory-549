# Campus GreenSwap Hub

A small Java web application built with JDBC, Servlets, and JSP.

## USP

This mini project solves a practical campus problem: students often buy duplicate books, gadgets, and supplies, then leave them unused. GreenSwap Hub lets them post items for free pickup or exchange, making campus sharing simple and sustainable.

## Core Features

- Add a new item listing
- View all available items
- Mark an item as claimed
- Delete a listing
- JDBC-backed persistence with MariaDB / MySQL

## Tech Stack

- Java Servlets
- JSP
- JDBC
- MariaDB / MySQL
- Plain HTML and CSS

## Database

The app uses the same credentials as the earlier JDBC labs by default, but they are now configurable through `web.xml` context params for easier deployment:

- URL: `jdbc:mariadb://localhost:3306/jdbc_demo`
- User: `eremika`
- Password: `Mikasa`

You can override them with `db.url`, `db.user`, `db.password`, and `db.driver`.

For Windows with MySQL, replace all four values together with `jdbc:mysql://localhost:3306/jdbc_demo`, `root`, your MySQL root password, and `com.mysql.cj.jdbc.Driver`. The matching commented alternatives are available in `DBConfig.java` and `WEB-INF/web.xml`. Use MySQL Connector/J instead of the MariaDB Java client.

## Setup

1. Create the database tables using `db/schema.sql`.
2. Put the project into your Tomcat webapps folder.
3. Compile the servlet classes with the servlet API and MariaDB connector on the classpath.
4. Open `index.jsp` in the browser through Tomcat.

## Production-Ready Touches

- Input validation before insert
- Context-based database configuration
- Escaped output in the listing page
- Post/Redirect/Get flow after create, claim, and delete actions
- Duplicate-safe claim handling

## Main URLs

- `/index.jsp` - home page
- `/add-item.jsp` - posting form
- `/list-items` - listing page
- `/claim-item` - mark item as claimed
- `/delete-item` - delete item

## Why this project works well as a mini project

- It looks modern but stays small enough to finish quickly.
- It demonstrates JDBC CRUD clearly.
- It has a real-world angle that feels more current than a basic registration form.
