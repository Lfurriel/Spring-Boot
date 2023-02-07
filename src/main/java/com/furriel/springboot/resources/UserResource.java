package com.furriel.springboot.resources;

import com.furriel.springboot.entities.Log;
import com.furriel.springboot.entities.User;
import com.furriel.springboot.repositories.LogRepository;
import com.furriel.springboot.services.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.context.annotation.PropertySource;

import java.net.URI;
import java.time.Instant;
import java.util.List;


@RestController
@PropertySource({"classpath:/messages.properties"})
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private Environment env;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <User> findbyId(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
       obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
       return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) throws JsonProcessingException {
        Long idCore = null;
        Log log = new Log(null, null , Instant.now(),"/users/" + id, "USERS",
                false, "", env.getProperty("MSG.RECEBIDO"), "");
        logRepository.save(log);

        idCore = log.getId();

        service.update(id, obj, idCore);

        return ResponseEntity.ok().body(obj);

    }
}
