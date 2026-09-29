@echo off
setlocal EnableExtensions EnableDelayedExpansion

cd /d "%~dp0"
if errorlevel 1 (
    echo [ERROR] Could not open the project folder.
    exit /b 1
)

set "SKIP_COMPILE="
set "INPUT_PATH="

if /I "%~1"=="noc" (
    set "SKIP_COMPILE=1"
    set "INPUT_PATH=%~2"
) else if /I "%~1"=="--no-compile" (
    set "SKIP_COMPILE=1"
    set "INPUT_PATH=%~2"
) else (
    set "INPUT_PATH=%~1"
)

if not defined INPUT_PATH if defined JDBC_JAR set "INPUT_PATH=%JDBC_JAR%"

if not defined INPUT_PATH if exist "%USERPROFILE%\jars\" (
    for /f "delims=" %%F in ('dir /b /s "%USERPROFILE%\jars\mysql-connector*.jar" 2^>nul') do (
        if not defined INPUT_PATH set "INPUT_PATH=%%F"
    )
)

if not defined INPUT_PATH (
    echo MySQL Connector/J was not found automatically.
    set /p "INPUT_PATH=Enter the full path to its .jar file or extracted folder: "
)

if not defined INPUT_PATH (
    echo [ERROR] No JDBC driver path was provided.
    exit /b 1
)

set "JAR="
if exist "!INPUT_PATH!\." (
    for /f "delims=" %%F in ('dir /b /s "!INPUT_PATH!\mysql-connector*.jar" 2^>nul') do (
        if not defined JAR set "JAR=%%F"
    )
    if not defined JAR (
        echo [ERROR] No mysql-connector*.jar was found inside:
        echo         !INPUT_PATH!
        exit /b 1
    )
) else (
    set "JAR=!INPUT_PATH!"
)

if /I not "!JAR:~-4!"==".jar" (
    echo [ERROR] Provide a Connector/J .jar file or the folder containing it.
    echo         !JAR!
    exit /b 1
)
if not exist "!JAR!" (
    echo [ERROR] JDBC driver not found:
    echo         !JAR!
    exit /b 1
)

where javac >nul 2>&1
if errorlevel 1 (
    echo [ERROR] javac was not found. Install a JDK and add its bin folder to PATH.
    exit /b 1
)
where java >nul 2>&1
if errorlevel 1 (
    echo [ERROR] java was not found. Install a JDK and add its bin folder to PATH.
    exit /b 1
)

echo [OK] Using JDBC driver: !JAR!

if not exist "out" mkdir out
if not defined SKIP_COMPILE (
    echo [Compiling] RegistrationApp.java...
    javac -cp "!JAR!" -d out src\RegistrationApp.java
    if errorlevel 1 (
        echo [ERROR] Compilation failed.
        exit /b 1
    )
    echo [OK] Compilation successful.
)

echo [Running] JDBC Registration Demo...
java -cp "!JAR!;out" RegistrationApp
exit /b !ERRORLEVEL!
