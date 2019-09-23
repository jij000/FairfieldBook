package edu.mum.cs.model;

import lombok.Getter;

@Getter
public enum SystemRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String SystemRoleName;

    private SystemRole(String SystemRoleName) {
        this.SystemRoleName = SystemRoleName;
    }
}