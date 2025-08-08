@echo off
echo ========================================
echo Eclipse Project Configuration Fixer
echo ========================================
echo.

echo This script will help fix the JDK configuration issue by:
echo 1. Backing up current settings
echo 2. Removing problematic configuration
echo 3. Providing instructions for reconfiguration
echo.

echo Creating backup of current settings...
if not exist "backup_settings" mkdir backup_settings
if exist ".settings" (
    xcopy ".settings" "backup_settings\.settings" /E /I /Y
    echo Settings backed up to backup_settings\.settings
)

echo.
echo Removing problematic Eclipse settings...
if exist ".settings" (
    rmdir /S /Q ".settings"
    echo Removed .settings folder
)

echo.
echo ========================================
echo NEXT STEPS
echo ========================================
echo.
echo 1. Close Eclipse completely
echo 2. Reopen Eclipse
echo 3. Import your project (File -^> Import -^> Existing Projects into Workspace)
echo 4. When prompted, select Java 15 as the compliance level
echo 5. If you have a working JDK 15 installed, Eclipse should automatically detect it
echo.
echo If Eclipse still has issues:
echo - Go to Window -^> Preferences -^> Java -^> Installed JREs
echo - Remove any JRE pointing to AdoptOpenJDK jre-11.0.10.9-hotspot
echo - Add a new JRE pointing to a working JDK 15 installation
echo - Set it as the default JRE
echo.
echo To restore original settings (if needed):
echo - Copy backup_settings\.settings back to .settings
echo.

pause

