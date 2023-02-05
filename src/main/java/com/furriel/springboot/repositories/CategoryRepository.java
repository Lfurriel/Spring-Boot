package com.furriel.springboot.repositories;

import com.furriel.springboot.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
