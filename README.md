# Hyperion Planning Utilities Formatters

## Purpose

The files in this folder are for creating a JAR file that would be used to provide console output for the Planning command line utilities. In 11.1.2.3 by default none of the Planning command line utilities actually log any output to the console. This is a change in behaviour from 11.1.2.1 and can cause issues if you rely on that output for any batch operations. The files in this folder are aimed to restore that functionality.

## Installation

Edit the file `E:\Oracle\Middleware\user_projects\epmsystemPLN1\Planning\planning1\loggingCLU.xml` so that the contents look similar to the below:

```
<?xml version="1.0" encoding="utf-8"?>
<logging_configuration>
    <log_handlers>
        <log_handler name='util-console-handler' class='oracle.core.ojdl.logging.ConsoleHandler' level='TRACE:16' filter='com.custom.filters.PlanningUtilFilter' formatter='com.custom.formatters.UtilFormat'/>
        <log_handler name="epmhp-planningCLU-handler" class="oracle.core.ojdl.logging.ODLHandlerFactory" level='NOTIFICATION:1'>
            <property name="path" value="${logging.folder}/PlanningCLU.log" />
            <property name="maxFileSize" value="10000000" />
            <property name="maxLogSize" value="100000000" />
            <property name="encoding" value="UTF-8" />
            <property name="useSourceClassAndMethod" value="true" />
            <property name="keepOpen" value="false" />
        </log_handler>
        <log_handler name="epmreg-handler" class="oracle.core.ojdl.logging.ODLHandlerFactory">
            <property name="path" value="${logging.folder}/SharedServices_Registry.log" />
            <property name="maxFileSize" value="10000000" />
            <property name="maxLogSize" value="50000000" />
        </log_handler>
        <log_handler name="epmcss-handler" class="oracle.core.ojdl.logging.ODLHandlerFactory">
            <property name="path" value="${logging.folder}/SharedServices_Security.log" />
            <property name="maxFileSize" value="10000000" />
            <property name="maxLogSize" value="50000000" />
            <property name="useSourceClassAndMethod" value="true" />
        </log_handler>
    </log_handlers>
    <loggers>
        <logger level="TRACE:16" name="oracle.EPMHSP" useParentHandlers="false">
            <handler name="epmhp-planningCLU-handler" />
            <handler name="util-console-handler" />
        </logger>
        <logger level="NOTIFICATION:1" name="oracle.EPMREG" useParentHandlers="false">
            <handler name="epmreg-handler" />
        </logger>
        <logger level="NOTIFICATION:1" name="oracle.EPMCSS" useParentHandlers="false">
            <handler name="epmcss-handler" />
        </logger>
    </loggers>
</logging_configuration>

```

This will send TRACE:16 log level logs to the console, and NOTIFICATION:1 level logs to the `PlanningCLU.log` file. The Planning utilities add a timestamp string to the beginning of each log message, so to not complicate the output we use a custom log formatter. Log messages will look like:
```
Thu Jan 16 17:12:18 GMT 2020 :: Logging into the application
```

There is another issue when we do this - the utilities are very chatty. There are so many log messages printed to the screen it actually locks up the user session while they print. To avoid this we use a custom log filter, that filters out any logging strings that we know are unneccessary.

To create the JAR file that includes these log formatters and filters, just run `build.bat`. The JAR file needs to be installed to E:\Oracle\Middleware\EPMSystem11R1\products\Planning\lib\UtilFilter.jar.

Once these steps have been completed invoking `ExportSecurity.cmd` will result in output to the command prompt similar to the below:
```
Thu Jan 16 17:12:12 GMT 2020 :: User Name=user1,appName=Sample,searchCriterianull,userSearchCriterianull,groupSearchCriterianull,valuesDelimiter=,,fileName=D:\Sample.txt,debug=false
Thu Jan 16 17:12:18 GMT 2020 :: Logging into the application
Thu Jan 16 17:12:22 GMT 2020 :: Logged into the application
Thu Jan 16 17:12:22 GMT 2020 :: Fetching the security list
Thu Jan 16 17:12:22 GMT 2020 :: Fetching all the security accesses
Thu Jan 16 17:12:22 GMT 2020 :: Done fetching the security list
Thu Jan 16 17:12:22 GMT 2020 :: start writing access to the file
Thu Jan 16 17:12:22 GMT 2020 :: Getting group name which are similar to group name.
Thu Jan 16 17:12:24 GMT 2020 :: Done writing access to the file
Thu Jan 16 17:12:24 GMT 2020 :: Done exporting the Security.
```
