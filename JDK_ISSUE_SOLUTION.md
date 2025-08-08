# JDK Issue Resolution Guide

## Problem Description
The error "Failed to init ct.sym for C:\Program Files\AdoptOpenJDK\jre-11.0.10.9-hotspot\lib\jrt-fs.jar" indicates that Eclipse is trying to use a corrupted or incompatible AdoptOpenJDK 11 installation, while your project is configured for Java 15.

## Root Cause
- Your project is configured for Java 15 (as seen in `.settings/org.eclipse.jdt.core.prefs`)
- Eclipse is trying to use AdoptOpenJDK 11 JRE which has a corrupted `ct.sym` file
- Version mismatch between project requirements and available JRE

## Solution Options

### Option 1: Quick Fix (Recommended)
1. **Run the diagnostic script:**
   ```
   fix_jdk_issue.bat
   ```
   This will check your system for available JDK installations.

2. **Run the Eclipse project fixer:**
   ```
   fix_eclipse_project.bat
   ```
   This will remove problematic settings and guide you through reconfiguration.

3. **Follow the on-screen instructions** to reconfigure Eclipse with a working JDK 15.

### Option 2: Manual Eclipse Configuration
1. **Open Eclipse**
2. **Go to Window → Preferences → Java → Installed JREs**
3. **Remove the problematic JRE:**
   - Find and remove: `C:\Program Files\AdoptOpenJDK\jre-11.0.10.9-hotspot`
4. **Add a new JRE:**
   - Click "Add"
   - Choose "Standard VM"
   - Browse to a working JDK 15 installation (e.g., `C:\Program Files\Java\jdk-15.0.2`)
   - Set it as the default JRE
5. **Clean and rebuild your project:**
   - Project → Clean...
   - Select your project and click OK

### Option 3: Project-Specific Configuration
1. **Right-click on your project → Properties**
2. **Go to Java Build Path → Libraries**
3. **Remove the JRE System Library**
4. **Add Library → JRE System Library**
5. **Choose the correct JRE (JDK 15)**
6. **Apply and Close**

## Installing JDK 15 (if needed)

If you don't have JDK 15 installed, download one of these:

### Eclipse Temurin (Recommended)
- **Download:** https://adoptium.net/temurin/releases/?version=15
- **Installation path:** `C:\Program Files\Eclipse Adoptium\jdk-15.0.2`

### Oracle JDK 15
- **Download:** https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html
- **Installation path:** `C:\Program Files\Java\jdk-15.0.2`

### Microsoft OpenJDK 15
- **Download:** https://docs.microsoft.com/en-us/java/openjdk/download
- **Installation path:** `C:\Program Files\Microsoft\jdk-15.0.2`

## Verification Steps

After applying the fix:

1. **Check Java version in Eclipse:**
   - Window → Preferences → Java → Installed JREs
   - Verify the default JRE is JDK 15

2. **Clean and rebuild:**
   - Project → Clean...
   - Build the project

3. **Check for errors:**
   - The "Failed to init ct.sym" error should be resolved
   - Your project should build successfully

## Troubleshooting

### If the error persists:
1. **Check JAVA_HOME environment variable:**
   ```
   echo %JAVA_HOME%
   ```
   Make sure it points to a JDK 15 installation.

2. **Verify JDK installation:**
   ```
   java -version
   javac -version
   ```

3. **Reset Eclipse workspace:**
   - Close Eclipse
   - Delete the `.metadata` folder from your workspace
   - Restart Eclipse and reimport projects

### If you need to restore original settings:
- Copy `backup_settings\.settings` back to `.settings` folder

## Prevention

To avoid this issue in the future:
1. **Use consistent JDK versions** across your development environment
2. **Regularly update JDK installations** to avoid corruption
3. **Use Eclipse Temurin** instead of older AdoptOpenJDK versions
4. **Keep project Java compliance settings** aligned with your JDK version

## Support

If you continue to experience issues:
1. Run `fix_jdk_issue.bat` and share the output
2. Check Eclipse error log: Window → Show View → Error Log
3. Verify your JDK installation is not corrupted

