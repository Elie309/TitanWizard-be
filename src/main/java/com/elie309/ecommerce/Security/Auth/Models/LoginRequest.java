package com.elie309.ecommerce.Security.Auth.Models;

public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isValid(LoginRequest loginRequest) {
        if (loginRequest.email == null || loginRequest.email.isEmpty()) {
            return false;
        }
        return loginRequest.password != null && !loginRequest.password.isEmpty();
    }
}