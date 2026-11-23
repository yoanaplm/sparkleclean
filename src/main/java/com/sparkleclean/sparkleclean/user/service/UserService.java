package com.sparkleclean.sparkleclean.user.service;

import com.sparkleclean.sparkleclean.exception.ResourceNotFoundException;
import com.sparkleclean.sparkleclean.user.model.User;
import com.sparkleclean.sparkleclean.user.model.UserRole;
import com.sparkleclean.sparkleclean.user.repository.UserRepository;
import com.sparkleclean.sparkleclean.web.dto.UserLoginDto;
import com.sparkleclean.sparkleclean.web.dto.UserRegisterDto;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sparkleclean.sparkleclean.exception.ResourceNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register (UserRegisterDto dto ){
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new RuntimeException("Passwords do not match");
        }

        Optional<User> optionalUser=userRepository.findByUsername(dto.getUsername());
        if(optionalUser.isPresent()){
            throw new RuntimeException("A user with this username already exists");

        }
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("A user with this email already exists");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.CLIENT);

        userRepository.save(user);
    }

    public User login(UserLoginDto dto){
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw  new RuntimeException("Invalid username or password");
        }return user;
    }
    public void createAdminIfMissing(String username,String email, String rawPassword){
        if( userRepository.findByUsername(username).isPresent()){
            return;
        }
        User admin =new User();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(rawPassword));
        admin.setRole(UserRole.ADMIN);
        userRepository.save(admin);
    }

    public User getById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }
}
