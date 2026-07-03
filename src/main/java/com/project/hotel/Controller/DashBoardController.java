package com.project.hotel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.hotel.DTO.DashBoardDTO;
import com.project.hotel.Service.DashBoardService;
@RestController
@RequestMapping("/api")
public class DashBoardController 
{
    @Autowired
    private DashBoardService dashboardService;

    @GetMapping("/dashboard")
    public DashBoardDTO getDashboard() {
        return dashboardService.getDashboardData();
    }
}
