package com.project.hotel.Service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.hotel.DTO.ForgotPasswordDTO;
import com.project.hotel.Entity.User;
import com.project.hotel.Repository.UserRepository;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String resetPassword(ForgotPasswordDTO dto) {

        System.out.println("SERVICE CALLED");
        System.out.println("Username: " + dto.getUsername());

        Optional<User> optionalUser =
                userRepository.findByUsername(
                        dto.getUsername().trim());

        System.out.println("Found: " +
                optionalUser.isPresent());

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            System.out.println("Old Password: "
                    + user.getPassword());

            user.setPassword(
                    passwordEncoder.encode(
                            dto.getNewPassword()));

            userRepository.save(user);

            System.out.println("Saved.");

            return "Password updated successfully";
        }

        return "User not found";
    }
}
