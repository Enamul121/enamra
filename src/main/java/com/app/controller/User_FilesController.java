package com.app.controller;


import com.app.model.Pager;
import com.app.model.User;
import com.app.model.User_files;
import com.app.repository.User_File_Repo;
import com.app.service.UserService;
import com.app.service.User_File_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/files")
public class User_FilesController {

    @Autowired
    private User_File_Service fileService;



    @Autowired
    private User_File_Repo user_file_repo;


    @Autowired
    private UserService userService;

    @GetMapping("/view")
    public ModelAndView viewUserImages(Principal principal){
        Optional<User> optionalUser = Optional.ofNullable(userService.fidUserByEmail(principal.getName()));

        User user = userService.findUserById(optionalUser.get().getId());

        ModelAndView model = new ModelAndView("user/view_img");
       List<User_files> file_img = user_file_repo.last60Records(user.getId());
       model.addObject("img_Lists", file_img);
        return model;
    }

    @GetMapping("/del/{file_id}")
    public ModelAndView deleteImg(@PathVariable("file_id") Long id){
        ModelAndView model = new ModelAndView("redirect:/profile");
        fileService.deleteFile(id);
        return model;

    }


}
