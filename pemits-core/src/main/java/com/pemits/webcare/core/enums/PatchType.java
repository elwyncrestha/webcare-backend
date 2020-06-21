package com.pemits.webcare.core.enums;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
public enum PatchType {
    INITIAL_USER("initial_user.sql");

    private final String value;

    PatchType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
