#!/usr/bin/env bash
# Compile (if needed) and run the app.
# Usage:  ./run.sh        -> compile + run
#         ./run.sh noc    -> run only (skip compile)

JAR=~/.dbvis/drivers/maven/org/mariadb/jdbc/mariadb-java-client/3.5.9/mariadb-java-client-3.5.9.jar

cd "$(dirname "$0")"

if [ "$1" != "noc" ]; then
    echo "Compiling..."
    javac -cp "$JAR" -d out src/RegistrationApp.java || exit 1
fi

java -cp "$JAR:out" RegistrationApp
