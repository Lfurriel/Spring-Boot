package com.furriel.springboot.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furriel.springboot.entities.Log;
import com.furriel.springboot.entities.User;
import com.furriel.springboot.repositories.LogRepository;
import com.furriel.springboot.repositories.UserRepository;
import com.furriel.springboot.services.exceptions.DatabaseException;
import com.furriel.springboot.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    LogRepository logRepository;
    @Autowired
    private Environment env;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
       Optional <User> obj = repository.findById(id);
       return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getLocalizedMessage());
        }
    }

    public User update(Long id, User obj, Long idCore) throws JsonProcessingException {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            User response = repository.save(entity);

            String payload = (new ObjectMapper()).writeValueAsString(response);
            logRepository.save(new Log(null, idCore, Instant.now(), "/users/" + id, "USERS",
                    false,"", env.getProperty("MSG.UPDATEUSER"), payload));

            return response;

        } catch (EntityNotFoundException e) {
            String payload = (new ObjectMapper()).writeValueAsString(obj);
            logRepository.save(new Log(null, idCore, Instant.now(), "/users/" + id, "USERS",
                    true, e.getLocalizedMessage(), env.getProperty("MSG.NUPDATEUSER"), payload));
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
