package com.app.service;


import com.app.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface AdminService {

    void saveAdmin(User user);
    void saveHR(User user);
    void saveManager(User user);


    List<User> getAllAdmin();  // testing ok
    List<User> getAllHR();
    List<User> getAllManager();

    void deleteAdminByID(Long id);
    void deleteManagerByID(Long id);
    void deleteHR_ByID(Long id);




}
