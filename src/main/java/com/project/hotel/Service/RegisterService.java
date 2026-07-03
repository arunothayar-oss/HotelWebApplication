package com.project.hotel.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.hotel.DTO.RegisterDTO;
import com.project.hotel.Entity.User;
import com.project.hotel.Repository.UserRepository;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String saveUser(RegisterDTO registerDTO) {

        User user = new User();

        user.setFullName(registerDTO.getFullName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPhoneNumber(registerDTO.getPhoneNumber());

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(
                        registerDTO.getPassword()));

        user.setRole(registerDTO.getRole());

        userRepository.save(user);

        return "Registration Successful";
    }
}

