package com.sparkleclean.sparkleclean.config;

import com.sparkleclean.sparkleclean.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {
 private final UserService userService;
    private final String username;
    private final String email;
    private final String password;

    public AdminSeeder(UserService userService, @Value("${app.admin.username}") String username,  @Value("${app.admin.email}") String email, @Value("${app.admin.password}") String password) {
        this.userService = userService;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public void run(String... args){
        userService.createAdminIfMissing(username,email,password);
    }
}
