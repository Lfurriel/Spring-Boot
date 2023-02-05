package com.furriel.springboot.config;

import com.furriel.springboot.entity.User;
import com.furriel.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Scarancio", "scarancio@gmail.com", "19997684080", "senhaScarancio");
        User u2 = new User(null, "Calva", "calva@gmail.com", "17991841600", "senhaCalva");
        User u3 = new User(null, "Jujugatita", "juju.gatinha@gmail.com", "17996801041", "senhaJujuba");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));
    }
}
