package com.project.hotel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.hotel.Entity.Feedback;
public interface FeedbackRepository   extends JpaRepository<Feedback, Long>
{
    
}
