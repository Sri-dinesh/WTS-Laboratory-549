# JDBC Registration Demo (Core Java + MySQL/MariaDB)

A tiny console app to learn the basics of JDBC:
connecting to a database, INSERT, SELECT, DELETE with `PreparedStatement`.

Everything is in **one file** (`src/RegistrationApp.java`) so you can read it top to bottom.

## Files

```
jdbc-connection/
├── src/RegistrationApp.java -- the whole app: connection settings, SQL, menu
├── setup.sql                -- creates the database + table
├── run.sh                   -- one-command compile+run (Linux/macOS)
└── README.md
```

The file is organized as:
1. **Database settings** at the top (URL, USER, PASSWORD constants).
2. **main()** — connects to the DB **once** at startup, then shows the menu.
3. **One method per menu action**, each showing a different JDBC concept:
   - `connect()`        → opens the single shared connection
   - `register()`       → INSERT with `PreparedStatement` placeholders
   - `listAll()`        → SELECT many rows via `ResultSet`
   - `findByEmail()`    → SELECT one row by a `?` parameter
   - `deleteByEmail()`  → DELETE, `executeUpdate()` row count
   - `findEmailExists()` → tiny helper (duplicate check)

---

# Setup & Run — Linux / macOS

## 1. One-time setup

1. Make sure MariaDB/MySQL is running locally.
2. Create the database and table:

   ```bash
   mysql -u root -p < setup.sql
   ```

3. You need the **MariaDB JDBC driver jar**. Download it from:
   <https://mariadb.com/downloads/connectors/connectors-data-access/java8-client>
   (or from Maven Central: search "mariadb-java-client"). Save it somewhere, e.g. `~/jars/`.

4. Open `src/RegistrationApp.java` and check the constants at the top
   (URL, USER, PASSWORD) match your local database.

## 2. Compile & run

**Easy way (script):**

```bash
# open run.sh and fix the JAR= path to point to YOUR driver jar first!
cd jdbc-connection
./run.sh          # compile + run
./run.sh noc      # run only, skip compile
```

**Manual way:**

```bash
cd jdbc-connection
JAR=~/jars/mariadb-java-client-3.5.9.jar     # path to your driver jar

javac -cp "$JAR" -d out src/RegistrationApp.java   # compile
java  -cp "$JAR:out" RegistrationApp               # run
```

---

# Setup & Run — Windows

## 1. One-time setup

1. Install **Java JDK** (check with `java -version` and `javac -version` in Command Prompt).
2. Install **MariaDB** (or MySQL) — during install it asks for a root password; remember it.
   Make sure the **MariaDB service is running** (it starts automatically; check in
   `services.msc` — look for "MariaDB").
3. Add the MariaDB `bin` folder to your PATH so `mariadb`/`mysql` works in Command Prompt,
   or just use the full path, e.g.:

   ```bat
   "C:\Program Files\MariaDB 11.4\bin\mariadb.exe" -u root -p < setup.sql
   ```

   Run this **from inside the `jdbc-connection` folder** so it finds `setup.sql`
   (easiest: open the folder in Explorer, type `cmd` in the address bar and press Enter).
4. Download the **MariaDB JDBC driver jar** from
   <https://mariadb.com/downloads/connectors/connectors-data-access/java8-client>
   and save it somewhere fixed, e.g. `C:\jars\mariadb-java-client-3.5.9.jar`.
5. Open `src\RegistrationApp.java` in Notepad/editor and check the constants at the top
   (URL, USER, PASSWORD) match your local database.

## 2. Compile & run (Command Prompt)

```bat
cd path\to\jdbc-connection
set JAR=C:\jars\mariadb-java-client-3.5.9.jar

javac -cp "%JAR%" -d out src\RegistrationApp.java
java  -cp "%JAR%;out" RegistrationApp
```

Note the two Windows differences:
- classpath separator is **`;`** (semicolon) instead of `:`
- path separator is **`\`** instead of `/`

(PowerShell users: use `$env:JAR = "C:\jars\mariadb-java-client-3.5.9.jar"` instead of `set`, 
and quote `"$env:JAR;out"` in the java command.)

## 3. What you should see

```
=== JDBC Registration Demo ===
Connected to the database!

1. Register a new user
2. List all users
...
```

---

# What to notice while reading the code

- `DriverManager.getConnection(...)` — how a JDBC connection is opened (once, at startup).
- `PreparedStatement` with `?` placeholders — safe way to pass values (no SQL injection).
- `try-with-resources` — statements/result sets are closed automatically.
- `executeUpdate()` for INSERT/DELETE vs `executeQuery()` for SELECT.
- `ResultSet` — how rows are read back.
