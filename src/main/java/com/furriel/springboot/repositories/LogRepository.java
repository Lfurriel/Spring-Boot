package com.furriel.springboot.repositories;

import com.furriel.springboot.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LogRepository extends JpaRepository<Log, Long> {
}
