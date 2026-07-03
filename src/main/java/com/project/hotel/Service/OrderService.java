package com.project.hotel.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.DTO.OrderDTO;
import com.project.hotel.DTO.OrderItemDTO;
import com.project.hotel.Entity.Order;
import com.project.hotel.Entity.OrderItem;
import com.project.hotel.Repository.OrderItemRepository;
import com.project.hotel.Repository.OrderRepository;

@Service
public class OrderService
 {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public String saveOrder(OrderDTO dto) {

        Order order = new Order();

        order.setCustomerName(dto.getCustomerName());
        order.setTableNumber(dto.getTableNumber());
        order.setGrandTotal(dto.getGrandTotal());

        Order savedOrder = orderRepository.save(order);

        for (OrderItemDTO itemDTO : dto.getItems()) {

            OrderItem item = new OrderItem();

            item.setOrderId(savedOrder.getOrderId());
            item.setItemName(itemDTO.getItemName());
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(itemDTO.getPrice());
            item.setTotal(itemDTO.getTotal());

            orderItemRepository.save(item);
        }

        return "Order Saved Successfully";
    }
}
