package com.project.hotel.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.hotel.DTO.FeedbackDTO;
import com.project.hotel.Entity.Feedback;
import com.project.hotel.Repository.FeedbackRepository;



@Service
public class FeedbackService
 {
     @Autowired
    private FeedbackRepository repository;

    public Feedback saveFeedback(FeedbackDTO dto)
    {
        Feedback feedback = new Feedback();

        feedback.setCustomerName(dto.getCustomerName());
        feedback.setEmail(dto.getEmail());
        feedback.setRating(dto.getRating());
        feedback.setMessage(dto.getMessage());

        return repository.save(feedback);
    }

    public long getFeedbackCount()
    {
        return repository.count();
    }
}
