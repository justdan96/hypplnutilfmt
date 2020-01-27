SET PATH=%PATH%;E:\Oracle\Middleware\jdk160_35\bin
SET JAVA_HOME=E:\Oracle\Middleware\jdk160_35

del /q UtilFilter.jar
mkdir build

javac -d build D:\custom\custom_log_filter\com\custom\filters\*.java
javac -d build D:\custom\custom_log_filter\com\custom\formatters\*.java

cd build
jar cvf ..\UtilFilter.jar *
cd ..

del /s /q build
rmdir /s /q build