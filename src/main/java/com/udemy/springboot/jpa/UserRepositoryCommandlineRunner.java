package com.udemy.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Optional;

public class UserRepositoryCommandlineRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserRepositoryCommandlineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("Rahul 2", "SE3"));

        Optional<User> userWithIdOne = userRepository.findById(1L);
        logger.info("User is retrived : " + userWithIdOne);

        List<User> user = userRepository.findAll();
        logger.info("List of user : " + user);
    }
}

