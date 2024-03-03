package com.elie309.titanwizard.security.auth.Models;

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
        //No need to validate password other than if it is empty since its is a login
        if (loginRequest.email == null || loginRequest.email.isEmpty()) {
            return false;
        }
        return loginRequest.password != null && !loginRequest.password.isEmpty();
    }
}