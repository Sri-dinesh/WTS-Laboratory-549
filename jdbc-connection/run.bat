@echo off
REM JDBC Registration Demo - Build & Run Script for Windows
REM Works with Command Prompt or PowerShell
REM Usage:
REM   run.bat                      - Compile & run (will prompt for JAR path if needed)
REM   run.bat "C:\path\to\jar"     - Compile & run with specified JAR
REM   run.bat noc "C:\path\to\jar" - Run only (skip compile)

setlocal enabledelayedexpansion

echo.
echo ======================================
echo JDBC Registration Demo - Build ^& Run
echo ======================================
echo.

REM Determine JDBC JAR path
set JAR=%2
if not defined JAR (
    if not "%1"=="noc" (
        set JAR=%1
    )
)

if not defined JAR (
    if defined JDBC_JAR (
        set JAR=!JDBC_JAR!
    )
)

REM Check common default locations
if not defined JAR (
    if exist "C:\Users\%USERNAME%\jars\mysql-connector-java-8.0.33.jar" (
        set JAR=C:\Users\%USERNAME%\jars\mysql-connector-java-8.0.33.jar
    )
)

REM Prompt user if JAR not found
if not defined JAR (
    echo [WARNING] JDBC JAR not found in default locations
    echo Please provide the path to mysql-connector-java-*.jar
    echo.
    set /p JAR="Enter the full path to the JDBC JAR: "
)

REM Verify JAR exists
if not exist "!JAR!" (
    echo.
    echo [ERROR] JDBC JAR not found at: !JAR!
    echo Please download MySQL Connector/J from:
    echo https://dev.mysql.com/downloads/connector/j/
    echo.
    pause
    exit /b 1
)

echo [OK] Using JDBC JAR: !JAR!
echo.

REM Create output directory
if not exist "out" mkdir out

REM Compile unless "noc" flag is passed
if not "%1"=="noc" (
    echo [Compiling] Compiling Java source files...
    javac -cp "!JAR!" -d out src\RegistrationApp.java
    if errorlevel 1 (
        echo [ERROR] Compilation failed
        echo.
        pause
        exit /b 1
    )
    echo [OK] Compilation successful
    echo.
)

REM Run the application
echo [Running] Starting JDBC Registration Demo...
echo.
echo ========================================
java -cp "!JAR!;out" RegistrationApp
echo ========================================
echo.
echo [OK] Application finished
echo.
pause
