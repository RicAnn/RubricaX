@echo off
echo Building RubricaX...

rem Compile Java files
javac -d bin src/*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

rem Create JAR
jar cfm RubricaX.jar MANIFEST.MF -C bin .
if %errorlevel% neq 0 (
    echo JAR creation failed!
    pause
    exit /b 1
)

echo Build completed successfully!
echo JAR file: RubricaX.jar
pause