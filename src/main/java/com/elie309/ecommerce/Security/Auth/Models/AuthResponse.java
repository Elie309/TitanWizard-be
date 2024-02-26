package com.elie309.ecommerce.Security.Auth.Models;

public record AuthResponse(String token) {

    public static AuthResponse build(String token) {
        return new AuthResponse(token);
    }
}
