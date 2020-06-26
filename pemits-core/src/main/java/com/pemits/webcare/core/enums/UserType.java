package com.pemits.webcare.core.enums;

/**
 * @author Elvin Shrestha on 6/26/2020
 */
public enum UserType {
    SUPER_ADMINISTRATOR("Super Administrator"),
    ADMINISTRATOR("Administrator");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
