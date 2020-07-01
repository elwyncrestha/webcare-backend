package com.pemits.webcare.core.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHERS("Others");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
