@echo off
echo ========================================
echo JDK 15 Installation Helper
echo ========================================
echo.

echo This script will help you install JDK 15 to resolve the Eclipse issue.
echo.

echo Choose your preferred JDK distribution:
echo 1. Eclipse Temurin (Recommended - Open Source)
echo 2. Oracle JDK (Commercial - requires account)
echo 3. Microsoft OpenJDK (Open Source)
echo.

set /p choice="Enter your choice (1-3): "

if "%choice%"=="1" (
    echo.
    echo Opening Eclipse Temurin download page...
    echo Download the Windows x64 MSI installer for JDK 15
    echo URL: https://adoptium.net/temurin/releases/?version=15
    echo.
    start https://adoptium.net/temurin/releases/?version=15
    echo Please download and install Eclipse Temurin JDK 15
    echo Installation path will be: C:\Program Files\Eclipse Adoptium\jdk-15.0.2
) else if "%choice%"=="2" (
    echo.
    echo Opening Oracle JDK download page...
    echo Note: Oracle JDK requires an Oracle account
    echo URL: https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html
    echo.
    start https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html
    echo Please download and install Oracle JDK 15
    echo Installation path will be: C:\Program Files\Java\jdk-15.0.2
) else if "%choice%"=="3" (
    echo.
    echo Opening Microsoft OpenJDK download page...
    echo URL: https://docs.microsoft.com/en-us/java/openjdk/download
    echo.
    start https://docs.microsoft.com/en-us/java/openjdk/download
    echo Please download and install Microsoft OpenJDK 15
    echo Installation path will be: C:\Program Files\Microsoft\jdk-15.0.2
) else (
    echo Invalid choice. Please run the script again.
    goto :end
)

echo.
echo ========================================
echo AFTER INSTALLATION
echo ========================================
echo.
echo 1. After installing JDK 15, restart Eclipse
echo 2. Go to Window -^> Preferences -^> Java -^> Installed JREs
echo 3. Add the new JDK 15 installation
echo 4. Set it as the default JRE
echo 5. Clean and rebuild your project
echo.
echo To verify installation, run: fix_jdk_issue.bat
echo.

:end
pause

