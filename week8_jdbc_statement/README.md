# Week 8: JDBC with Statement

## Objective

Write a JDBC program to create an employee table in a database with fields employee no, name, salary and department. Perform operations on the table like insert, retrieve, update and delete operations (CRUD) using Statement.

## Pre-Lab Questions

1. What is JDBC?
2. Write down the steps to connect a Java application with sql database?
3. What is a driver? List out the different types of JDBC drivers?
4. What packages need to be imported for working on JDBC?
5. How do we configure the system to work with JDBC?
6. Write an SQL query to create a table, insert rows into the table, retrieve the result, update a row in a table and delete a row in a table?

## Programs

### 8(i) - CreateTable.java

Creates an employee table in MariaDB database with fields:

- no (integer)
- name (varchar)
- salary (integer)
- department (varchar)

### 8(ii) - InsertTable.java

Inserts employee records into the emp1 table using Statement and Scanner input.

### 8(iii) - Retrieve.java

Retrieves and displays all employee records from the emp1 table.

### 8(iv) - Update.java

Updates the salary of a specific employee by their employee number.

### 8(v) - Delete.java

Deletes a specific employee record from the emp1 table by employee number.

## Setup Instructions

You need the **MariaDB JDBC driver jar**. Download it from:
<https://mariadb.com/downloads/connectors/connectors-data-access/java8-client>
(or from Maven Central: search "mariadb-java-client")

Then add it to your classpath before compiling and running the programs.

## Compilation and Execution

```bash
javac CreateTable.java
java CreateTable

javac InsertTable.java
java InsertTable

javac Retrieve.java
java Retrieve

javac Update.java
java Update

javac Delete.java
java Delete
```

## Database Setup

- Database name: jdbc_demo
- User: eremika
- Password: Mikasa
- Driver: org.mariadb.jdbc.Driver
- URL: jdbc:mariadb://localhost:3306/jdbc_demo

## Post-Lab Questions

1. Which driver is best suitable to work on with JDBC?
2. List out the methods to execute a query in java program?
3. Write down the driver URL for MySQL and SQL.
4. What is the use of ResultSet Class?
5. What is the usage of DriverManager class?
6. Which function is to take input from the console in java?
