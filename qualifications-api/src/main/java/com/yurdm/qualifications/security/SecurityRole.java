package com.yurdm.qualifications.security;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SecurityRole {
    COMPANY("COMPANY"),
    ADMIN("ADMIN"),
    LECTURER("LECTURER"),
    STUDENT("STUDENT");


    private final String role;

    SecurityRole(String role) {
        this.role = role;
    }

    @JsonValue
    String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return getRole();
    }
}
