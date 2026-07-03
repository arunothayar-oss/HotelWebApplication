package com.project.hotel.Service;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.hotel.Entity.User;
import com.project.hotel.Repository.UserRepository;

@Service
public class CustomerUserDetailsService  implements UserDetailsService
 {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {
          System.out.println("Entered Username: " + username);
        User user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"));
        System.out.println("DB Username: " + user.getUsername());
    System.out.println("DB Password: " + user.getPassword());
    System.out.println("DB Role: " + user.getRole());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.getRole()))
        );
    }
}
