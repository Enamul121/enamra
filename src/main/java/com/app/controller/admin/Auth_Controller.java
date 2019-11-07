package com.app.controller.admin;


import com.app.model.User;
import com.app.service.AdminService;
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
@RequestMapping("/admin")
public class Auth_Controller {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;



    @GetMapping("/create/admin")
    public ModelAndView createAdmin(){
        ModelAndView model = new ModelAndView("admin_cc/signup");
        model.addObject("user", new User());
        return model;
    }


 @PostMapping("/create/admin")
    public ModelAndView CreateAdmin(@Valid User user, BindingResult bindingResult){

     ModelAndView model = new ModelAndView();

     User userExist = userService.fidUserByEmail(user.getEmail());
     if (userExist != null){
         bindingResult.rejectValue("email", "error.user", "This email already exists!");
     }
     if (!user.getPassword().equals(user.getConfirmPassword())){
         bindingResult.rejectValue("password", "error.user", "Password didnt match!!!!");
     }

     if (bindingResult.hasErrors()) {
         model.setViewName("admin_cc/signup");
     } else {
         adminService.saveAdmin(user);
         model.addObject("msg", "User has been registered successfully!");
         model.addObject("user", new User());
         model.setViewName("admin_cc/signup");
     }
     return model;

    }



    @GetMapping("/create/hr")
    public ModelAndView createHR(){
        ModelAndView model = new ModelAndView("admin_cc/hr_signup");
        model.addObject("user", new User());
        return model;
    }


    @PostMapping("/create/hr")
    public ModelAndView CreateHR(@Valid User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();

        User userExist = userService.fidUserByEmail(user.getEmail());
        if (userExist != null){
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("password", "error.user", "Password didnt match!!!!");
        }

        if (bindingResult.hasErrors()) {
            model.setViewName("admin_cc/hr_signup");
        } else {
            adminService.saveHR(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("admin_cc/hr_signup");
        }
        return model;
    }




    @GetMapping("/create/manager")
    public ModelAndView createMANAGER(){
        ModelAndView model = new ModelAndView("admin_cc/manager_signup");
        model.addObject("user", new User());
        return model;
    }

    @PostMapping("/create/manager")
    public ModelAndView CreateMANAGER(@Valid User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();

        User userExist = userService.fidUserByEmail(user.getEmail());
        if (userExist != null){ bindingResult.rejectValue("email", "error.user", "This email already exists!"); }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            bindingResult.rejectValue("password", "error.user", "Password didnt match!!!!"); }

        if (bindingResult.hasErrors()) {
            model.setViewName("admin_cc/manager_signup");
        } else {
            adminService.saveManager(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("admin_cc/manager_signup");
        }
        return model;
    }


    @GetMapping("/del/admin/{id}")
    public ModelAndView deleteAdmin(@PathVariable("id")Long id){
        adminService.deleteAdminByID(id);
        return new ModelAndView("redirect:/admin_Dashboard");
    }

    @GetMapping("/del/hr/{id}")
    public ModelAndView deleteHR(@PathVariable("id") Long id){
        adminService.deleteHR_ByID(id);
        return new ModelAndView("redirect:/admin_Dashboard");
    }

    @GetMapping("/del/manager/{id}")
    public ModelAndView deleteManger(@PathVariable("id") Long id){
        adminService.deleteManagerByID(id);
        return new ModelAndView("redirect:/admin_Dashboard");
    }




}
