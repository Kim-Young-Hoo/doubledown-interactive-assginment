package com.doubledown.assignment.responses;

public class JwtResponse {
    private String token;
    private String type = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getters
    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    // Setters (if necessary)
    public void setToken(String token) {
        this.token = token;
    }

    public void setType(String type) {
        this.type = type;
    }
}
