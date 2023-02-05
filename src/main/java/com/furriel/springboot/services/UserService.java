package com.furriel.springboot.services;

import com.furriel.springboot.entities.User;
import com.furriel.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
       Optional <User> obj = repository.findById(id);
       return obj.get();
    }
}
