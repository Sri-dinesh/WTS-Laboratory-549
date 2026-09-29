# JDBC Setup Guide for Windows

This guide walks you through setting up and running the **JDBC Registration Demo** on Windows. We'll install MySQL, configure it, set up the JDBC driver, and run the application.

---

## Table of Contents

1. [Install MySQL](#1-install-mysql)
2. [Configure MySQL](#2-configure-mysql)
3. [Verify Java Installation](#3-verify-java-installation)
4. [Download JDBC Driver](#4-download-jdbc-driver)
5. [Configure the Project](#5-configure-the-project)
6. [Compile and Run](#6-compile-and-run)
7. [Troubleshooting](#7-troubleshooting)

---

## 1. Install MySQL

### Step 1.1: Download MySQL Installer

1. Go to [MySQL Official Downloads](https://dev.mysql.com/downloads/mysql/)
2. Select your Windows version (64-bit recommended)
3. Download the **MySQL Installer** (standalone version)

### Step 1.2: Run the Installer

1. Double-click the downloaded `.msi` file
2. Choose **"Setup Type"**: Select **Custom** for more control
3. In the product list:
   - Select **MySQL Server** (any recent version like 8.0.x)
   - Click **"Next"**
4. Click **"Execute"** to download and install the files
5. Click **"Next"** to continue

### Step 1.3: Configure MySQL Server

1. Choose **"Standalone MySQL Server / Classic MySQL Server"**
2. Click **"Next"**
3. Set the following:
   - **Port**: `3306` (default is fine)
   - **MySQL Root Password**: Set a strong password (e.g., `root123`) — **remember this!**
4. Click **"Next"** → **"Execute"** → **"Finish"**

### Step 1.4: Verify Installation

Open **Command Prompt** (Win + R, type `cmd`, press Enter) and run:

```cmd
mysql --version
```

You should see something like: `mysql  Ver 8.0.x for Windows on x86_64`

---

## 2. Configure MySQL

### Step 2.1: Log in to MySQL

Open **Command Prompt** and run:

```cmd
mysql -u root -p
```

When prompted, enter the root password you set during installation.

You should see the `mysql>` prompt.

### Step 2.2: Create Database

Copy and paste this into the MySQL prompt:

```sql
CREATE DATABASE jdbc_demo;
USE jdbc_demo;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

Then press **Enter**. You should see `Query OK` messages.

### Step 2.3: Create a Database User (Optional but Recommended)

Instead of using the `root` user for the application, create a dedicated user:

```sql
CREATE USER 'dbuser'@'localhost' IDENTIFIED BY 'DbPassword123';
GRANT ALL PRIVILEGES ON jdbc_demo.* TO 'dbuser'@'localhost';
FLUSH PRIVILEGES;
```

This creates a user `dbuser` with password `DbPassword123` (you can change these to anything you prefer).

### Step 2.4: Exit MySQL

Type:

```sql
EXIT;
```

---

## 3. Verify Java Installation

You need **Java Development Kit (JDK)** version 8 or higher.

### Step 3.1: Check Java Version

Open **Command Prompt** and run:

```cmd
java -version
```

You should see something like:

```
java version "11.0.x" 2021-XX-XX
```

### Step 3.2: Install Java (If Not Already Installed)

If Java is not installed:

1. Go to [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)
2. Download **JDK 11** (LTS) or newer for Windows
3. Run the installer and follow the on-screen instructions
4. During installation, note the installation path (e.g., `C:\Program Files\Java\jdk-11.0.x`)

### Step 3.3: Add Java to System PATH (If Needed)

If `java -version` doesn't work after installation:

1. **Press** Win + X → **Settings**
2. Search for **"Edit the system environment variables"**
3. Click **"Environment Variables"**
4. Under **"System variables"**, click **"New"**
5. Add:
   - **Variable name**: `JAVA_HOME`
   - **Variable value**: `C:\Program Files\Java\jdk-11.0.x` (your JDK path)
6. Click **"OK"** → **"OK"** → **"OK"**
7. Restart **Command Prompt** and test again with `java -version`

---

## 4. Download JDBC Driver

The JDBC driver is the Java library that lets your Java code talk to MySQL.

### Step 4.1: Download MySQL Connector/J JDBC Driver

1. Go to [MySQL Connector/J Downloads](https://dev.mysql.com/downloads/connector/j/)
2. Under **"Select Operating System"**, choose **Platform Independent**
3. Download the `.jar` file (e.g., `mysql-connector-java-8.x.x.jar`)
4. **Save it to a folder** you can easily access, e.g.:
   ```
   C:\Users\YourUsername\jars\
   ```
   (Create the `jars` folder if it doesn't exist)

### Step 4.2: Note the Full Path

After downloading, you'll have a file like:

```
C:\Users\YourUsername\jars\mysql-connector-java-8.0.33.jar
```

**Keep this path handy** — you'll need it later when compiling and running. (Note: The exact version number may differ; use whatever you downloaded.)

---

## 5. Configure the Project

### Step 5.1: Clone/Download the Project

You can get the JDBC project code from GitHub in two ways:

#### Option A: Clone with Git (Recommended)

If you have **Git** installed on your Windows machine:

1. Open **Command Prompt** or **PowerShell**
2. Navigate to where you want to store the project:
   ```cmd
   cd C:\Users\YourUsername\Projects
   ```
3. Clone the repository:
   ```cmd
   git clone https://github.com/Sri-dinesh/WTS-Laboratory-549.git
   ```
4. Navigate into the project:
   ```cmd
   cd WTS-Laboratory-549\jdbc-connection
   ```

#### Option B: Download as ZIP (If Git Not Installed)

1. Go to [https://github.com/Sri-dinesh/WTS-Laboratory-549](https://github.com/Sri-dinesh/WTS-Laboratory-549)
2. Click the green **"< > Code"** button
3. Select **"Download ZIP"**
4. Extract the ZIP file to your desired location (e.g., `C:\Users\YourUsername\Projects`)
5. Open Windows Explorer and navigate to the `WTS-Laboratory-549\jdbc-connection` folder

### Step 5.2: Open the Project Files

Navigate to the `jdbc-connection` folder in your Windows Explorer or Command Prompt:

```cmd
cd path\to\jdbc-connection
```

Example:

```cmd
cd C:\Users\YourUsername\Projects\WTS-Laboratory-549\jdbc-connection
```

### Step 5.3: Check Database Credentials

Open `src/RegistrationApp.java` in a text editor (Notepad++ or VS Code recommended).

Find these lines near the top:

```java
private static final String URL      = "jdbc:mysql://localhost:3306/jdbc_demo";
private static final String USER     = "dbuser";
private static final String PASSWORD = "DbPassword123";
```

**Update them** to match your MySQL setup:

- If you used the `root` user, change `USER` to `root` and `PASSWORD` to your root password
- If you created the `dbuser` user in Step 2.3, leave them as-is (or use your own credentials)
- Keep the URL the same (it points to `jdbc_demo` database on `localhost`)

**Save the file.**

### Step 5.4: Verify `setup.sql`

The `setup.sql` file contains SQL commands to create the database and table. You've already run these in Step 2.2, but check that it looks like:

```sql
CREATE DATABASE IF NOT EXISTS jdbc_demo;
USE jdbc_demo;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 6. Compile and Run

### Step 6.1: Open Command Prompt in Project Folder

Press **Win + R**, type `cmd`, press **Enter**.

Navigate to your project:

```cmd
cd C:\Users\YourUsername\Projects\practice-stuff\wts_lab_programs\jdbc-connection
```

### Step 6.2: Create Output Directory

Create a folder to hold compiled `.class` files:

```cmd
mkdir out
```

### Step 6.3: Compile the Java File

Replace `C:\path\to\mysql-connector.jar` with your actual jar path:

```cmd
javac -d out -cp "C:\Users\YourUsername\jars\mysql-connector-java-8.0.33.jar" src/RegistrationApp.java
```

**Example:**

```cmd
javac -d out -cp "C:\Users\YourUsername\jars\mysql-connector-java-8.0.33.jar" src/RegistrationApp.java
```

If successful, you'll see **no error messages**. The compiled `.class` file will be in the `out` folder.

### Step 6.4: Run the Application

```cmd
java -cp "C:\Users\YourUsername\jars\mysql-connector-java-8.0.33.jar;out" RegistrationApp
```

You should see:

```
=== JDBC Registration Demo ===
Connected to the database!

1. Register a new user
2. List all users
3. Find user by email
4. Delete user by email
5. Exit
Choose:
```

### Step 6.5: Test the Application

Try the following:

1. **Register a user**: Choose option `1`, enter name, email, phone
2. **List users**: Choose option `2` to see all registered users
3. **Find by email**: Choose option `3` and enter an email
4. **Delete a user**: Choose option `4` and enter an email
5. **Exit**: Choose option `5`

---

## 7. Troubleshooting

### Issue: `mysql` command not found

**Solution:**

- Add MySQL to your system PATH:
  1. Find your MySQL installation folder (usually `C:\Program Files\MySQL\MySQL Server 8.0\bin`)
  2. Add it to your system PATH (same steps as Java in Section 3.3)
  3. Restart Command Prompt

### Issue: `java` command not found

**Solution:**

- See Section 3.3 to add Java to your system PATH and restart Command Prompt

### Issue: "Access denied for user 'dbuser'@'localhost'"

**Solution:**

- Double-check the `USER` and `PASSWORD` in `RegistrationApp.java`
- Verify they match the MySQL user you created in Step 2.3
- Or change them to `root` and your root password

### Issue: "Cannot find symbol" or "class not found" during compilation

**Solution:**

- Make sure the `-cp` (classpath) includes the correct path to the JDBC jar file
- Use the full path to the jar, e.g., `C:\Users\...\mysql-connector-java-8.0.33.jar`
- No spaces in the path, or wrap it in quotes

### Issue: "Connection refused" or "No suitable driver found"

**Solution:**

- Verify MySQL is running (check Windows Services: search for "Services", look for "MySQL80")
- If MySQL stopped, restart it from Services
- Double-check the connection URL in `RegistrationApp.java` matches your MySQL setup
- Ensure the JDBC jar is in the classpath when running the app

### Issue: "Database 'jdbc_demo' doesn't exist"

**Solution:**

- Log back into MySQL and run the setup commands from Section 2.2:
  ```cmd
  mysql -u root -p < setup.sql
  ```
  (Replace `root` with your username if different)

### Issue: "Duplicate entry" for email when registering

**Solution:**

- The `users` table has a UNIQUE constraint on the email column
- Try registering with a different email address, or delete the existing user first

---

## Quick Reference: Complete Command Sequence

If you just want to copy-paste, here's the complete sequence:

### First Time Setup (Terminal)

```cmd
# Navigate to project
cd C:\Users\YourUsername\Projects\practice-stuff\wts_lab_programs\jdbc-connection

# Create output folder
mkdir out

# Compile
javac -d out -cp "C:\Users\YourUsername\jars\mysql-connector-java-8.0.33.jar" src/RegistrationApp.java

# Run
java -cp "C:\Users\YourUsername\jars\mysql-connector-java-8.0.33.jar;out" RegistrationApp
```

### MySQL Setup (Command Prompt)

```cmd
# Log into MySQL
mysql -u root -p

# Then paste these in MySQL prompt:
CREATE DATABASE jdbc_demo;
USE jdbc_demo;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
EXIT;
```

---

## Next Steps

- Explore the `RegistrationApp.java` file to understand how JDBC works
- Modify the table structure in `setup.sql` and experiment with new columns
- Add new menu options (e.g., update user info, search by name)
- Try connecting to a remote MySQL database instead of localhost

**Happy coding!**
