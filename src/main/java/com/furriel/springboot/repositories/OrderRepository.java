package com.furriel.springboot.repositories;

import com.furriel.springboot.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
