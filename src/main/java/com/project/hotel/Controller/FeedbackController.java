package com.project.hotel.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.hotel.DTO.FeedbackDTO;

import com.project.hotel.Service.FeedbackService;

@RestController

public class FeedbackController
 {
      @Autowired
    private FeedbackService service;

    @PostMapping("/api/feedback")
    public String saveFeedback(
            @RequestBody FeedbackDTO dto)
    {
        service.saveFeedback(dto);

        return "Feedback Submitted Successfully!";
    }

    @GetMapping("/api/feedback/count")
    public long getFeedbackCount()
    {
        return service.getFeedbackCount();
    }
}
