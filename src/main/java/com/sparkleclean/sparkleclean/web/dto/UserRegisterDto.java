package com.sparkleclean.sparkleclean.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {


    @NotBlank(message = "Username required")
    @Size(min=3,max=20,message = "Username must be between 3 and 20 characters.")
    private String username;


    @NotBlank(message = "Email required")
    @Email(message = "Enter a valid email address.")
    private String email;


    @NotBlank(message = "Password required")
    @Size(min=3,max=20,message = "Password must be between 3 and 20 characters.")
    private String password;


    @NotBlank(message = "Please confirm your password.")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
