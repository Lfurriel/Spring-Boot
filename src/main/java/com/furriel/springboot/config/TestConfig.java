package com.furriel.springboot.config;

import com.furriel.springboot.entity.Order;
import com.furriel.springboot.entity.User;
import com.furriel.springboot.entity.enums.OrderStatus;
import com.furriel.springboot.repositories.OrderRepository;
import com.furriel.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Scarancio", "scarancio@gmail.com", "19997684080", "senhaScarancio");
        User u2 = new User(null, "Calva", "calva@gmail.com", "17991841600", "senhaCalva");
        User u3 = new User(null, "Jujugatita", "juju.gatinha@gmail.com", "17996801041", "senhaJujuba");
        User u4 = new User(null, "Pedrogás", "peter.steam@gmail.com", "14981373100", "helio123");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.DELIVERED, u2);

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
