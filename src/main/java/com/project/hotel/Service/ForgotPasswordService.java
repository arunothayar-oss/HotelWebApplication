package com.project.hotel.Service;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.project.hotel.DTO.ForgotPasswordDTO;
import com.project.hotel.Entity.User;
import com.project.hotel.Repository.UserRepository;

@Service
public class ForgotPasswordService 
{
         @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    // Stores OTP temporarily
    private Map<String, String> otpStorage = new HashMap<>();


    // Generate 6-digit OTP
    public String generateOTP()
    {
        Random random = new Random();

        int otp = 100000 + random.nextInt(900000);

        return String.valueOf(otp);
    }


    // Send OTP Email
    public void sendEmail(String email, String otp)
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);

        message.setSubject("Hotel Management System - Password Reset OTP");

        message.setText(
                "Your OTP for password reset is : "
                        + otp
                        + "\n\nThis OTP is valid until you use it.");

        mailSender.send(message);
    }


    // Send OTP
    public String sendOTP(ForgotPasswordDTO dto)
    {

        Optional<User> optionalUser =
                userRepository.findByUsernameAndRole(
                        dto.getUsername().trim(),
                        dto.getRole());

        if(optionalUser.isEmpty())
        {
            return "Invalid Username or Role";
        }

        User user = optionalUser.get();

        String otp = generateOTP();

        otpStorage.put(user.getUsername(), otp);

        sendEmail(user.getEmail(), otp);

        return "OTP Sent Successfully";
    }


    // Reset Password
    public String resetPassword(ForgotPasswordDTO dto)
    {

        Optional<User> optionalUser =
                userRepository.findByUsernameAndRole(
                        dto.getUsername().trim(),
                        dto.getRole());

        if(optionalUser.isEmpty())
        {
            return "Invalid Username or Role";
        }

        String storedOTP =
                otpStorage.get(dto.getUsername());

        if(storedOTP == null)
        {
            return "Please Generate OTP First";
        }

        if(!storedOTP.equals(dto.getOtp()))
        {
            return "Invalid OTP";
        }

        User user = optionalUser.get();

        user.setPassword(
                passwordEncoder.encode(
                        dto.getNewPassword()));

        userRepository.save(user);

        // Remove OTP after successful password reset
        otpStorage.remove(dto.getUsername());

        return "Password Updated Successfully";
    }
   
}
