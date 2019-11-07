package com.app.service.impl;

import com.app.model.Role;
import com.app.model.User;
import com.app.repository.ManagerRepo;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import com.app.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepo managerRepo;

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public void saveChief_Instructor(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(1);
        Role ci_role = roleRepository.findByRole("CHIF INSTRUCTOR");
        user.setRoles(new HashSet<Role>(Arrays.asList(ci_role)));
        managerRepo.save(user);
    }

    @Override
    public void saveInstructor(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(1);
        Role instructor_role = roleRepository.findByRole("INSTRUCTOR");
        user.setRoles(new HashSet<Role>(Arrays.asList(instructor_role)));
        managerRepo.save(user);

    }

    @Override
    public List<User> getAllChief_Instructor() {
        return managerRepo.findAll_ChiefInstructor_ByRoles();
    }

    @Override
    public List<User> getAllInstructor() {
        return managerRepo.findAll_Instructor_ByRoles();
    }

    @Override
    public void deleteChief_Instructor_ByID(Long id) {
        managerRepo.deleteById(id);
    }

    @Override
    public void deleteInstructorByID(Long id) {
        managerRepo.deleteById(id);
    }
}
