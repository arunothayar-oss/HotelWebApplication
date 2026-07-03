package com.project.hotel.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.hotel.DTO.OrderDTO;
import com.project.hotel.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController 
{
     @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public String saveOrder(@RequestBody OrderDTO dto) {

        return orderService.saveOrder(dto);
    }
}
