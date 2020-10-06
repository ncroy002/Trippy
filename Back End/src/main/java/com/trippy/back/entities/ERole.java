package com.trippy.back.entities;

public enum ERole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String role;

    ERole(String r) {
        this.role = r;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
