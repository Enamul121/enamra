package com.app.controller;

import com.app.EnamraApplication;
import com.app.model.Role;
import com.app.model.User;
import com.app.repository.AdminRepo;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import com.app.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnamraApplication.class)
public class AdminControllerTest {


    @Qualifier("adminRepo")
    @Autowired
    AdminRepo adminRepo;

    @Autowired
    AdminService adminService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;


    @Test
    public void name() {  // test OK
        List<User> adminList = adminRepo.findAllAdminByRoles();
        log.info("All Admin -> {}", adminList);
    }



    @Test
    public void getAllHr() {  // test OK
        List<User> adminList = adminRepo.findAllHR_By_Roles();
        log.info("All HR -> {}", adminList);
    }



    @Test
    public void getAllManager() {  // test OK
        List<User> adminList = adminRepo.findAllManagerByRoles();
        log.info("All Manager -> {}", adminList);
    }


    @Test
    @Transactional
    @DirtiesContext
    public void saveAdmin() {
        User user = new User();
        user.setFirstname("AA");
        user.setLastname("BB");
        user.setPassword(encoder.encode("123"));
        user.setEmail("from SPRING test 1");
        user.setActive(1);
        Role adminRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
        userRepository.save(user);

        log.info("Save ADMIN : -> {}", userRepository.save(user));
    }
}