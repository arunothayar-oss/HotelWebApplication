package com.project.hotel.Service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.hotel.DTO.LoginDTO;
import com.project.hotel.Repository.UserRepository;
import com.project.hotel.Entity.User;
@Service
public class LoginService
 {
     @Autowired
        private UserRepository userRepository;

    public String loginUser(LoginDTO loginDTO) 
    {

        Optional<User> userOptional =
                userRepository.findByUsername(
                        loginDTO.getUsername());
        System.out.println("Entered Username: " +
            loginDTO.getUsername());

    System.out.println("Entered Password: " +
            loginDTO.getPassword());

    System.out.println("Entered Role: " +
            loginDTO.getRole());                

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            System.out.println("DB Username: " +
                user.getUsername());

        System.out.println("DB Password: " +
                user.getPassword());

        System.out.println("DB Role: " +
                user.getRole());


            if (user.getPassword()
                    .equals(loginDTO.getPassword())
                    &&
                user.getRole()
                    .equalsIgnoreCase(loginDTO.getRole())) {

                return user.getRole();
            }
        }

        return "Invalid";
    }
}
