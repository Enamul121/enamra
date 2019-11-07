package com.app.controller;

import com.app.EnamraApplication;
import com.app.model.User;
import com.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnamraApplication.class)
public class UserController {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findRoleByID() {
/*
        User userRole = userRepository.findByRole("admin@admin.com");
        log.info("Role For User -> {}", userRole);*/
    }
}
