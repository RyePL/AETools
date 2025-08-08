@echo off
echo ========================================
echo AETools JDK Issue Fixer
echo ========================================
echo.

echo Checking Java installations...
echo.

echo Current JAVA_HOME:
echo %JAVA_HOME%
echo.

echo Available Java installations:
where java
echo.

echo Java version:
java -version
echo.

echo ========================================
echo Checking for the problematic JDK...
echo ========================================

if exist "C:\Program Files\AdoptOpenJDK\jre-11.0.10.9-hotspot" (
    echo WARNING: Found the problematic AdoptOpenJDK installation at:
    echo C:\Program Files\AdoptOpenJDK\jre-11.0.10.9-hotspot
    echo This installation appears to be corrupted.
    echo.
)

echo ========================================
echo Checking for alternative JDK installations...
echo ========================================

set "FOUND_JDK="

if exist "C:\Program Files\Java\jdk-15.0.2" (
    echo Found Oracle JDK 15 at: C:\Program Files\Java\jdk-15.0.2
    set "FOUND_JDK=C:\Program Files\Java\jdk-15.0.2"
)
if exist "C:\Program Files\Eclipse Adoptium\jdk-15.0.2" (
    echo Found Eclipse Temurin JDK 15 at: C:\Program Files\Eclipse Adoptium\jdk-15.0.2"
    set "FOUND_JDK=C:\Program Files\Eclipse Adoptium\jdk-15.0.2"
)
if exist "C:\Program Files\Microsoft\jdk-15.0.2" (
    echo Found Microsoft OpenJDK 15 at: C:\Program Files\Microsoft\jdk-15.0.2"
    set "FOUND_JDK=C:\Program Files\Microsoft\jdk-15.0.2"
)
if exist "C:\Program Files\AdoptOpenJDK\jdk-15.0.2" (
    echo Found AdoptOpenJDK 15 at: C:\Program Files\AdoptOpenJDK\jdk-15.0.2"
    set "FOUND_JDK=C:\Program Files\AdoptOpenJDK\jdk-15.0.2"
)

echo.
echo ========================================
echo SOLUTION STEPS
echo ========================================
echo.

if defined FOUND_JDK (
    echo 1. Open Eclipse
    echo 2. Go to Window -^> Preferences -^> Java -^> Installed JREs
    echo 3. Remove the problematic JRE: C:\Program Files\AdoptOpenJDK\jre-11.0.10.9-hotspot
    echo 4. Add a new JRE pointing to: %FOUND_JDK%
    echo 5. Set it as the default JRE
    echo 6. Clean and rebuild your project (Project -^> Clean...)
    echo.
    echo Alternative: Right-click on your project -^> Properties -^> Java Build Path -^> Libraries
    echo Remove the JRE System Library and add the correct one.
) else (
    echo No compatible JDK 15 found. Please install one of the following:
    echo - Oracle JDK 15
    echo - Eclipse Temurin JDK 15  
    echo - Microsoft OpenJDK 15
    echo - AdoptOpenJDK 15
    echo.
    echo Download links:
    echo - Oracle JDK: https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html
    echo - Eclipse Temurin: https://adoptium.net/temurin/releases/?version=15
    echo - Microsoft OpenJDK: https://docs.microsoft.com/en-us/java/openjdk/download
)

echo.
echo ========================================
echo QUICK FIX (if you have a working JDK)
echo ========================================
echo If you have a working JDK installed, you can also:
echo 1. Delete the .settings folder in your project
echo 2. Restart Eclipse
echo 3. Let Eclipse recreate the project settings
echo.

pause
