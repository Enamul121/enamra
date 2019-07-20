package com.app.service.impl;


import com.app.model.Role;
import com.app.model.User;
import com.app.repository.AdminRepo;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import com.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Qualifier("adminRepo")
    @Autowired
    private AdminRepo adminRepo;

    @Override
    public void saveAdmin(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(1);
        Role admin_role = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(admin_role)));
        adminRepo.save(user);
    }


    @Override
    public List<User> getAllAdmin() {
        return adminRepo.findAllAdminByRoles() ;
    }
}
