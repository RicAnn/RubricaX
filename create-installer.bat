@echo off
echo Creating Windows installer for RubricaX...

rem Check if running on Java 14+
java -version 2>&1 | findstr "version" | findstr /C:"14" /C:"15" /C:"16" /C:"17" /C:"18" /C:"19" /C:"20" /C:"21" >nul
if %errorlevel% neq 0 (
    echo JPackage requires Java 14 or higher
    pause
    exit /b 1
)

rem Build JAR first
call build.bat

rem Create native installer
jpackage --input . --name RubricaX --main-jar RubricaX.jar --main-class Main --type exe --win-shortcut --win-menu

echo Installer created successfully!
pause