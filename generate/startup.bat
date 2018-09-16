@echo on
rem Script to launch the command line interface for mnt launcher
set BASE_HOME=%~dp0
set JAVA_EXEC=%JAVA_HOME%\jre\bin\javaw
set CP=%BASE_HOME%\*;%BASE_HOME%\lib\*;
"%JAVA_EXEC%" -cp %CP% -Dfile.encoding=UTF-8 -Duser.language=en -Duser.country=US com.mnt.gui.fx.launcher.MNTFXLauncher
@if errorlevel 1 off
