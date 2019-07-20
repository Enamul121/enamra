package com.app.service;

import com.app.model.User;

public interface UserService {

    User fidUserByEmail(String email);

    User findUserById(Long id);

    void saveUser(User user);



}
