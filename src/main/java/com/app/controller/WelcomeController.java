package com.app.controller;


import com.app.model.Course;
import com.app.model.User;
import com.app.repository.CourseRepo;
import com.app.service.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class WelcomeController {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public ModelAndView welcome(){
        ModelAndView model = new ModelAndView("welcome");
            List<Course> first_four_from_last = courseRepo.recently_added_first_four_course();
            List<Course> second_4_from_last = courseRepo.recently_added_second_four_course();
            model.addObject("recently_4", first_four_from_last);
            model.addObject("second_4", second_4_from_last);
        return model;
    }

    @GetMapping("/home")
    public ModelAndView homePage(Principal principal){
        ModelAndView model = new ModelAndView("home");
        List<Course> first_four_from_last = courseRepo.recently_added_first_four_course();
        User userCC = userService.fidUserByEmail(principal.getName());
        String str = String.valueOf(userCC.getRoles());
        String STR = str.substring(1,str.length()-1);

        if (STR.equals("USER")){
            model.addObject("user", STR);
        }else if (STR.equals("ADMIN")){
            model.addObject("admin", STR);
        }else if (STR.equals("HR")){
            model.addObject("hr", STR);
        }else if (STR.equals("MANAGER")){
            model.addObject("manager", STR);
        }else if (STR.equals("CHIF INSTRUCTOR")){
            model.addObject("CHIF_INSTRUCTOR", STR);
        }else if (STR.equals("INSTRUCTOR")){
            model.addObject("INSTRUCTOR", STR);
        }else if (STR.equals("Assistant INSTRUCTOR")){
            model.addObject("Assistant_INSTRUCTOR", STR);
        }else if (STR.equals("Teaching Assistant")){
            model.addObject("Teaching_Assistant", STR);
        }else if (STR.equals("STUFF")){
            model.addObject("STUFF", STR);
        }else if (STR.equals("EMPLOYEE")){
            model.addObject("EMPLOYEE", STR);
        }

        List<Course> second_4_from_last = courseRepo.recently_added_second_four_course();
        model.addObject("recently_4", first_four_from_last);
        model.addObject("second_4", second_4_from_last);
        return model;
    }




}
