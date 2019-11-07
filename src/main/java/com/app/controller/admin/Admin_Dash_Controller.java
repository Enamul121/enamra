package com.app.controller.admin;


import com.app.model.User;
import com.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class Admin_Dash_Controller {

    @Autowired
    private AdminService adminService;


    @GetMapping("/admin_Dashboard")
    public ModelAndView adminDashBoard(){
        ModelAndView model = new ModelAndView("admin_cc/admin_dashboard");
        List<User> adminList = adminService.getAllAdmin();
        List<User> hrList = adminService.getAllHR();
        List<User> ManagerList = adminService.getAllManager();
        model.addObject("AdminList", adminList);
        model.addObject("hrList", hrList);
        model.addObject("managerList", ManagerList);
        return model;
    }





}
