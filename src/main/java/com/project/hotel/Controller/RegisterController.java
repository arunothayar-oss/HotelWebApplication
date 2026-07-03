package com.project.hotel.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.hotel.DTO.RegisterDTO;
import com.project.hotel.Service.RegisterService;
@RestController
@RequestMapping("/api")
public class RegisterController 
{
     @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDTO registerDTO)
     {
        return registerService.saveUser(registerDTO);
    }
}
