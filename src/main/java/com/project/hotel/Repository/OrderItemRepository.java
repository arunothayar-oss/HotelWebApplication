package com.project.hotel.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotel.Entity.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>
{
        List<OrderItem> findByOrderId(Integer orderId);
}
