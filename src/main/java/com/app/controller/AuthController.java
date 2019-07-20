package com.app.controller;

import com.app.model.User;
import com.app.service.AdminService;
import com.app.service.impl.AdminServiceImpl;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthController {


    @Autowired
    private UserService userService;

    @GetMapping("/user/home")
    public String home(){return "/user/home";}

    @GetMapping("/user/login")
    public ModelAndView signin(){
        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");
        return model;
    }


    @GetMapping("/user/signup")
    public ModelAndView signupPage(){
        ModelAndView model = new ModelAndView();
        model.addObject("user", new User());
        model.setViewName("user/signup");
        return model;
    }


    @PostMapping("/user/signup")
    public ModelAndView signup(@Valid User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();

        User userExist = userService.fidUserByEmail(user.getEmail());
        if (userExist != null){
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("password", "error.user", "Password didnt match!!!!");
        }

        if (bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("user/signup");
        }
        return model;
    }



}
