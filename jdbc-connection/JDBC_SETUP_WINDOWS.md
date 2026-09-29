# JDBC Registration Demo: Complete Windows Setup

This guide starts with a new Windows computer and takes you through installing MySQL and Java, preparing the project, and running the registration app.

## What you need

- Windows 10 or 11
- An internet connection for the installers
- This project folder, including `jdbc-connection`
- A MySQL account and its password

Complete the sections in order. You only need to install these tools once.

## 1. Install MySQL Server

1. Download the Windows installer from [MySQL Community Downloads](https://dev.mysql.com/downloads/installer/).
2. Run the installer. Choose a setup type that includes **MySQL Server**. If you select **Custom**, add MySQL Server before continuing.
3. Configure MySQL Server as a standalone server.
4. Keep the default classic protocol port, `3306`, unless you have a reason to use another port.
5. Choose the recommended authentication option and set a root password. Remember this password; you will enter it into the app configuration in Step 6.
6. Configure MySQL as a Windows service and enable it to start automatically. Complete the installer.

MySQL Shell may display `localhost:33060`. That is the X Protocol port. This Java app uses Connector/J with the classic protocol, normally on `3306`; do not put `33060` in the JDBC URL.

To check that the MySQL service is running, open the Windows **Services** app and look for a service such as `MySQL80`. Start it if it is stopped.

## 2. Install the Java JDK

Install JDK 17 or newer. A JDK includes both `java` and the `javac` compiler required by the run script.

Download a Windows JDK from [Oracle Java](https://www.oracle.com/in/java/technologies/downloads/#java27) or another trusted JDK provider. Run the installer and enable its option to add Java to `PATH` if offered.

Open a **new** PowerShell or Command Prompt window and check the installation:

```powershell
java -version
javac -version
```

Both commands should print version information. If either command is not found, finish configuring the JDK installer or add the JDK's `bin` folder to Windows `PATH`, then reopen the terminal.

## 3. Download and extract MySQL Connector/J

Connector/J is the JDBC driver that lets the Java app connect to MySQL.

1. Open [MySQL Connector/J downloads](https://dev.mysql.com/downloads/connector/j/).
2. Select the current Windows-independent archive (ZIP), download it, and extract it. If the download page offers a platform-independent ZIP archive, use that.
3. Find the extracted file named like `mysql-connector-j-<version>.jar`. It may be inside a nested folder with the same version name.
4. Keep the extracted folder or the JAR in a permanent, easy-to-find location, such as your Downloads folder or `C:\jars`.

Do not pass the downloaded ZIP file to the run script. Pass the extracted folder or the `.jar` file inside it.

## 4. Open the project folder

Open PowerShell or Command Prompt and change to the `jdbc-connection` folder in this project. For example:

```powershell
cd "C:\Users\YourName\OneDrive\Desktop\projects\WTS-Laboratory-549\jdbc-connection"
```

Replace the example with the actual location of your project. This folder should contain `run.bat`, `setup.sql`, and the `src` folder.

If you do not have the project yet, clone or download it from the [WTS-Laboratory-549 repository](https://github.com/Sri-dinesh/WTS-Laboratory-549), then open its `jdbc-connection` folder.

## 5. Create the application database

The project includes `setup.sql`. It creates the `jdbc_demo` database and the `users` table expected by the app. Run it once; it is safe to run again if the database and table already exist.

### Option A: Run it from MySQL Shell

Open MySQL Shell and connect to the MySQL server. At the SQL prompt, run `\source` followed by the full path to this project's `setup.sql`. Use forward slashes in the path, for example:

```text
\source C:/Users/YourName/OneDrive/Desktop/projects/WTS-Laboratory-549/jdbc-connection/setup.sql
```

Use your actual Windows username and project path. MySQL Shell should report that the statements completed successfully.

Verify the setup in MySQL Shell:

```sql
USE jdbc_demo;
SHOW TABLES;
```

The table list should contain `users`.

### Option B: Run it from Command Prompt

If the MySQL command-line client is installed and available on `PATH`, open Command Prompt in the `jdbc-connection` folder and run:

```cmd
mysql -u root -p < setup.sql
```

Enter the root password when prompted. If Windows says `mysql` is not recognized, use Option A or run `mysql.exe` by its full path.

## 6. Set the app's MySQL connection details

Open `src/RegistrationApp.java` in VS Code. Near the top, set the connection constants to match your MySQL Server and root account:

```java
private static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
private static final String USER = "root";
private static final String PASSWORD = "your-MySQL-root-password";
```

Replace `your-MySQL-root-password` with the root password you set in Step 1. If you configured a different classic protocol port, replace `3306` with that port. Save the file.

The app currently stores its password in the source file for this classroom demo. Do not use this pattern for a real service or commit a real password to a shared repository.

## 7. Compile and run the app

From the `jdbc-connection` folder, run `run.bat` and pass the extracted Connector/J folder. Replace the sample paths and version with yours:

```powershell
.\run.bat "C:\Users\YourName\Downloads\mysql-connector-j-<version>"
```

You can also pass the exact driver JAR:

```powershell
.\run.bat "C:\jars\mysql-connector-j-<version>.jar"
```

The script locates Connector/J, compiles `src\RegistrationApp.java` into the `out` folder, and starts the app. The first successful launch should show:

```text
=== JDBC Registration Demo ===
Connected to the database!

1. Register a new user
2. List all users
3. Find user by email
4. Delete user by email
5. Exit
Choose:
```

Select a menu option by typing its number and pressing Enter. Choose `5` to exit. The script can also be started by double-clicking `run.bat`, but running it from a terminal makes errors easier to read.

## Troubleshooting

**`java` or `javac` is not recognized**

Install a JDK, ensure its `bin` folder is on `PATH`, and open a new terminal. Check again with `java -version` and `javac -version`.

**The script says no Connector/J JAR was found**

Pass the extracted Connector/J folder or the `.jar` file itself. Do not pass the ZIP archive. The script searches inside the folder, including nested folders.

**`No suitable driver found`**

The Connector/J JAR was not included at runtime. Run `run.bat` with the correct JAR path or extracted folder.

**`Unsupported protocol version` or an X Protocol message**

The JDBC URL is using an X Protocol port. Use the MySQL classic protocol port, normally `3306`, not `33060`.

**`Connection refused`**

Check that the MySQL Server Windows service is running and that the port in the JDBC URL is the server's classic protocol port.

**`Access denied for user`**

Check `USER` and `PASSWORD` in `RegistrationApp.java`. They must match a MySQL account that can connect from `localhost` and access `jdbc_demo`.

**`Unknown database 'jdbc_demo'` or missing `users` table**

Run `setup.sql` again using one of the methods in Step 5.

**Duplicate email while registering**

Email addresses must be unique. Register with another email address or delete the existing user first.
