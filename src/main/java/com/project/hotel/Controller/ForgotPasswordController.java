package com.project.hotel.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotel.DTO.ForgotPasswordDTO;
import com.project.hotel.Service.ForgotPasswordService;
@RestController
public class ForgotPasswordController 
{
   
   @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgotpassword")
    public String resetPassword(
            @RequestBody ForgotPasswordDTO dto)
    {
        
     return forgotPasswordService.resetPassword(dto);
    }
}
