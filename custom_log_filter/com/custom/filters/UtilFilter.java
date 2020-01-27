package com.custom.filters;

import java.util.logging.Filter;
import java.util.logging.LogRecord;


public class UtilFilter implements Filter {

 public boolean isLoggable(LogRecord record) {
    return record.getSourceClassName().indexOf("com.hyperion.planning.UtilityLogger") != -1;
 }
}