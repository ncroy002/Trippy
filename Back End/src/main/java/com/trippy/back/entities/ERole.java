package com.trippy.back.entities;

public enum ERole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    ERole(String r) {
        this.role = r;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
