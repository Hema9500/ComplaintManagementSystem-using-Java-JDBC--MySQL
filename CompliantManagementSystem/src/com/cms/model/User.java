package com.cms.model;

public class User {

    private int userId;
    private String name;
    private String email;
    private String password;
    private String role;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // =========================================
    // USER ID
    // =========================================

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // =========================================
    // NAME
    // =========================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // =========================================
    // EMAIL
    // =========================================

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // =========================================
    // PASSWORD
    // =========================================

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // =========================================
    // ROLE
    // =========================================

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
