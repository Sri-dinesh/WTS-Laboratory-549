# Week 9: JDBC with PreparedStatement

## Objective

Write a JDBC program to create a product table in a database with fields product no, name, image and price. Perform operations on the table like insert, retrieve, update and delete operations (CRUD) using PreparedStatement.

## Pre-Lab Questions

1. What is the purpose of Prepared and Callable Statement?
2. Write down the steps to create a Prepared Statement?
3. Write down the steps to create a Callable Statement?
4. What is a Stored procedure?
5. What is a Stored function?
6. Differentiate between Stored Procedure and Stored Function?

## Programs

### 9(i) - CreateTablePrepared.java

Creates a products table in MariaDB database with fields:

- product_no (int)
- name (varchar)
- image (blob)
- price (int)

Uses PreparedStatement for secure table creation.

### 9(ii) - InsertTablePrepared.java

Inserts product records into the products table using PreparedStatement with parameterized queries.

- Securely handles image BLOB data
- Prevents SQL injection attacks

### 9(iii) - GetTablePrepared.java

Retrieves and displays all product records from the products table using PreparedStatement.

### 9(iv) - UpdateTablePrepared.java

Updates the price of a specific product by product number using PreparedStatement.

- Uses parameterized queries for security

### 9(v) - DeleteTablePrepared.java

Deletes a specific product record from the products table by product number using PreparedStatement.

## Setup Instructions

You need the **MariaDB JDBC driver jar**. Download it from:
<https://mariadb.com/downloads/connectors/connectors-data-access/java8-client>
(or from Maven Central: search "mariadb-java-client")

Then add it to your classpath before compiling and running the programs.

## Compilation and Execution

```bash
javac CreateTablePrepared.java
java CreateTablePrepared

javac InsertTablePrepared.java
java InsertTablePrepared

javac GetTablePrepared.java
java GetTablePrepared

javac UpdateTablePrepared.java
java UpdateTablePrepared

javac DeleteTablePrepared.java
java DeleteTablePrepared
```

## Database Setup

- Database name: jdbc_demo
- User: eremika
- Password: Mikasa
- Driver: org.mariadb.jdbc.Driver
- URL: jdbc:mariadb://localhost:3306/jdbc_demo

### Windows / MySQL alternative

The Java files contain commented alternatives for MySQL. Comment the active MariaDB driver and connection, then uncomment the MySQL values as one complete set: `com.mysql.cj.jdbc.Driver`, `jdbc:mysql://localhost:3306/jdbc_demo`, user `root`, and your MySQL root password. Use MySQL Connector/J for this configuration; do not mix it with the MariaDB driver.

## Key Differences: PreparedStatement vs Statement

1. **Security**: PreparedStatement prevents SQL injection attacks through parameterized queries
2. **Performance**: PreparedStatement can be reused multiple times
3. **Syntax**: Uses `?` placeholders for parameters
4. **Methods**: Uses `setInt()`, `setString()`, `setBlob()` etc. to set parameter values

## Post-Lab Questions

1. What are the advantages of using PreparedStatement over Statement?
2. How does PreparedStatement prevent SQL injection?
3. What is the purpose of parameter placeholders (?) in PreparedStatement?
4. When should you use PreparedStatement vs Statement?
5. How do you handle BLOB data in PreparedStatement?
6. What is the difference between `executeUpdate()` and `executeQuery()`?
