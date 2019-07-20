package com.app.controller;


import com.app.model.User;
import com.app.service.AdminService;
import com.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AdminController {


    String name;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    private AdminService adminService;

    @GetMapping("/admin/home")
    public String homeAdmin(){return "admin/home";}



    @GetMapping("/admin/signup")
    public ModelAndView createadminPage(){
        ModelAndView model = new ModelAndView();
        model.addObject("user", new User());
        model.setViewName("admin/signup");
        return model;

    }


    @PostMapping("/admin/signup")
    public ModelAndView signup(@Valid User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        if (!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("password", "error.user", "Password not match!!!!");
        }

        if (bindingResult.hasErrors()) {
            model.setViewName("admin/signup");
        } else {
            adminService.saveAdmin(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("admin/signup");
        }
        return model;

    }


    @GetMapping("/admin/list")
    public ModelAndView adminList(){
        ModelAndView model = new ModelAndView("admin/home");
        List<User> adminList = adminService.getAllAdmin();
        model.addObject("adminLIST", adminList);
        return model;
    }




}
