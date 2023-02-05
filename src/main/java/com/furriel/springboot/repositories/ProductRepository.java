package com.furriel.springboot.repositories;

import com.furriel.springboot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
