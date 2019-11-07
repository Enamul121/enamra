package com.app.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AcademicController {


    @GetMapping("/academic")
    public ModelAndView academic(){return new ModelAndView("user/academic");}


}
