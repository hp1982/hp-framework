@echo off


rem check java
if "%JAVA_HOME%" == "" goto noJavaHome


set DIR=%~dp0
set JAVA_OPTS=-Xms256m -Xmx256m -XX:NewRatio=1 -Xss256k -XX:+UseSerialGC -XX:CICompilerCount=2 -Xverify:none -XX:AutoBoxCacheMax=20000

"%JAVA_HOME%\bin\java" %JAVA_OPTS% -cp "%DIR%\hptop.jar;%JAVA_HOME%\lib\tools.jar" com.huipeng1982.hptop.HPTop %*
goto end

:noJavaHome
  echo Please set JAVA_HOME before running this script
  goto end
:end

pause
