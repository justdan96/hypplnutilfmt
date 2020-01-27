package com.custom.filters;

import java.util.logging.Filter;
import java.util.logging.LogRecord;


public class PlanningUtilFilter implements Filter {

 public boolean isLoggable(LogRecord record) {
    if (record.getSourceClassName().indexOf("com.hyperion.planning.UtilityLogger") != -1) {
      // I know this is ridiculous but it's the only way I've found to filter out the chatty log messages
      if (
       record.getMessage().indexOf(" :: matching with the group Name") != -1 ||
       record.getMessage().indexOf(" :: with user:-Founding the matching user name and group name as:-") != -1 ||
       record.getMessage().indexOf(" :: matching with the user Name  ") != -1 ||
       record.getMessage().indexOf(" :: with group:-Founding the matching user name and group name as:-") != -1 ||
       record.getMessage().indexOf(" :: More than one record with the name:-") != -1 ||
       record.getMessage().indexOf(" :: Returing the similar groups.") != -1 ||
       record.getMessage().indexOf(" :: Total Access found:-") != -1 ||
       record.getMessage().indexOf(" :: File Name provide ") != -1 ||
       record.getMessage().indexOf(" :: Appending the sl group for the group named =") != -1 ||
       record.getMessage().indexOf(" :: File Name provide ") != -1 ||
       record.getMessage().indexOf(" :: Total Access for the user:-") != -1 ||
       record.getMessage().indexOf(" :: No Users found for the search:-") != -1 ||
       record.getMessage().indexOf(" :: Total Access for the user and group for search:-") != -1 ||
       record.getMessage().indexOf(" :: No groups found for the search:-") != -1 ||
       record.getMessage().indexOf(" :: Fetching security accesses based on member") != -1 ||
       record.getMessage().indexOf(" :: Searching for the user named:-") != -1 ||
       record.getMessage().indexOf(" :: Searching for the group named:-") != -1
      ) {
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
 }
}