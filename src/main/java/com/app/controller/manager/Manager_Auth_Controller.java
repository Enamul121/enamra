package com.app.controller.manager;


import com.app.model.User;
import com.app.service.ManagerService;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager")
public class Manager_Auth_Controller {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;


    @GetMapping("/cc")
    public String cc(){return "manager/cc";}

    @GetMapping("/create/chief_instructor")
    public ModelAndView createChief_Instructor(){
        ModelAndView model = new ModelAndView("manager/chief_instructor_signup");
        model.addObject("user", new User());
        return model;
    }


    @PostMapping("/create/chief_instructor")
    public ModelAndView CreateChief_Instructor(@Valid User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();

        User userExist = userService.fidUserByEmail(user.getEmail());
        if (userExist != null){ bindingResult.rejectValue("email", "error.user", "This email already  exists!"); }

        if (!user.getPassword().equals(user.getConfirmPassword())){ bindingResult.rejectValue("password", "error.user", "Password didnt match!!!"); }

        if (bindingResult.hasErrors()) { model.setViewName("manager/chief_instructor_signup");
        } else {
            managerService.saveChief_Instructor(user);
            model.addObject("msg", "Chief Instructor has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("manager/chief_instructor_signup");
        }
        return model;
    }


    @GetMapping("/create/instructor")
    public ModelAndView createInstructor(){
        ModelAndView model = new ModelAndView("manager/instructor_signup");
        model.addObject("user", new User());
        return model;
    }


    @PostMapping("/create/instructor")
    public ModelAndView CreateInstructor(@Valid User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();

        User userExist = userService.fidUserByEmail(user.getEmail());
        if (userExist != null){ bindingResult.rejectValue("email", "error.user", "This email already  exists!!"); }

        if (!user.getPassword().equals(user.getConfirmPassword())){ bindingResult.rejectValue("password", "error.user", "Password didnt match!!!!"); }

        if (bindingResult.hasErrors()) { model.setViewName("manager/instructor_signup");
        } else {
            managerService.saveInstructor(user);
            model.addObject("msg", "Instructor has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("manager/instructor_signup");
        }
        return model;
    }


    // delete

    @GetMapping("/del/ci/{id}")
    public String delete_chief_instructor(@PathVariable("id") Long id){
        managerService.deleteChief_Instructor_ByID(id);
        return "redirect:/manager_dashboard";
    }

    @GetMapping("/del/instructor/{id}")
    public String delete_Instructor(@PathVariable("id") Long id){
        managerService.deleteInstructorByID(id);
        return "redirect:/manager_dashboard";
    }


}
