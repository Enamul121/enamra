package com.app.controller.hr;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hr")
public class HR_HomeController {



      @GetMapping("/hr_Dashboard")
     public ModelAndView hrDashBoard(){return new ModelAndView("hr/hr_dashboard");}

}
