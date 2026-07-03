package com.project.hotel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.hotel.DTO.LoginDTO;
import com.project.hotel.Service.LoginService;
@RestController
@RequestMapping("/api/login")
public class LoginController 
{
     @Autowired
    private LoginService loginService;

    @PostMapping
    public String loginUser(
            @RequestBody LoginDTO loginDTO) 
            {

        return loginService.loginUser(loginDTO);
    }
}
