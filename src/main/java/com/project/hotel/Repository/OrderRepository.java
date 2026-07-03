package com.project.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.hotel.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> 
{

}