package com.simplebot.model.enums;

/**
 * Created by Vladislav on 11/10/2016.
 */
public enum Command {
    LOG_TIME,
    UNDEFINED;

    public static boolean ifIsACommand(String command) {
        switch(command) {
            case "log time" : return true;
        }
        return false;
    }

    public static Command getCommandFromString(String command) {
        switch(command) {
            case "log time" : return LOG_TIME;
        }
        return UNDEFINED;
    }
}
