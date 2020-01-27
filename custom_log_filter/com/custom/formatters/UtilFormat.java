package com.custom.formatters;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;


public class UtilFormat extends Formatter {

    public synchronized String format(LogRecord record) {
        return record.getMessage() + "\n";

    }
}