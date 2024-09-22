package com.example.CIMR_DSI.Model;

public class LoginResponse {
    private String token;
    private Long id;

    public void setToken(String token) {
        this.token = token;
    }

    private Long expiresIn;

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and setters...
}