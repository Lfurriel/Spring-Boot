package com.furriel.springboot.repositories;

import com.furriel.springboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Não é necessário pois extends JPA (que ja é Repository)
public interface UserRepository extends JpaRepository<User, Long> {
}
