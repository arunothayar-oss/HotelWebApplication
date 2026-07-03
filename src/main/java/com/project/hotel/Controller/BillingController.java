package com.project.hotel.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.hotel.DTO.BillingDTO;
import com.project.hotel.Service.BillingService;

@RestController
@RequestMapping("/api/billing")
public class BillingController
 {
    @Autowired
    private BillingService billingService;

    @PostMapping("/save")
    public String saveBill(@RequestBody BillingDTO dto)
     {
        return billingService.saveBill(dto);
    }
}
