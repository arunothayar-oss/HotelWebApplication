package com.project.hotel.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.hotel.DTO.DashBoardDTO;
import com.project.hotel.Repository.FeedbackRepository;
import com.project.hotel.Repository.OrderRepository;
import com.project.hotel.Repository.BillingRepository;
@Service
public class DashBoardService 

{
     @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillingRepository billingRepository;

    public DashBoardDTO getDashboardData() {

        long feedbackCount =
                feedbackRepository.count();

        long totalOrders =
                orderRepository.count();

        double totalSales = billingRepository.getTotalSales();
              

        return new DashBoardDTO(
                totalOrders,
                totalSales,
                feedbackCount
        );
    }
}
