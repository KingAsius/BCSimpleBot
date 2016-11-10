package com.simplebot.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Vladislav on 11/10/2016.
 */
public enum Gender {
    MALE,
    FEMALE,
    UNDEFINED;

    @JsonCreator
    public static Gender fromString(String value) {
        switch(value) {
            case "male" : return MALE;
            case "female" : return FEMALE;
        }
        return UNDEFINED;
    }
}
