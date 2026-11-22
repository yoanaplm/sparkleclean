package com.sparkleclean.sparkleclean.web.dto;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDto {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password required")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
