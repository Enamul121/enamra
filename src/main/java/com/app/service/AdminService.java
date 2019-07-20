package com.app.service;


import com.app.model.User;

import java.util.List;

public interface AdminService {

    void saveAdmin(User user);

    List<User> getAllAdmin();


}
