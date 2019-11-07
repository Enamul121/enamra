package com.app.service;

import com.app.model.User;

import java.util.List;

public interface ManagerService {

    void saveChief_Instructor(User user);
    void saveInstructor(User user);



    List<User> getAllChief_Instructor();
    List<User> getAllInstructor();


    void deleteChief_Instructor_ByID(Long id);
    void deleteInstructorByID(Long id);
}
