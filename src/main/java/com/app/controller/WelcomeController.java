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
    public ModelAndView wellcome(){
        ModelAndView model = new ModelAndView("welcome");


            List<Course> first_four_from_last = courseRepo.recently_added_first_four_course();
            List<Course> second_4_from_last = courseRepo.recently_added_second_four_course();
            model.addObject("recently_4", first_four_from_last);
            model.addObject("second_4", second_4_from_last);







        return model;
    }




}
