package com.elie309.titanwizard.security.auth.Models;

public record AuthResponse(String token) {

    public static AuthResponse build(String token) {
        return new AuthResponse(token);
    }
}
