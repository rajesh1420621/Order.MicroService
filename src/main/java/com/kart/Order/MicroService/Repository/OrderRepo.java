package com.kart.Order.MicroService.Repository;

import com.kart.Order.MicroService.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity,Long> {
}
