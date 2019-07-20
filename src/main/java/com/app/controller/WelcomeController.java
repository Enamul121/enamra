package com.app.controller;


import com.app.model.Course;
import com.app.repository.CourseRepo;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    private CourseRepo courseRepo;


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
