package com.udemy.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    @Autowired
    UserDaoService userDaoService;

    private Logger logger = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        long id = userDaoService.insertUser(new User("Rahul","DE2"));
        logger.info("Generated user is : " + id);
    }
}
