package com.giulioarantes.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giulioarantes.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
