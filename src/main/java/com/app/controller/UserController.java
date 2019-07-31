package com.app.controller;


import com.app.exception.InvalidFileException;
import com.app.model.Profile_pic;
import com.app.model.User;
import com.app.service.Profile_pic_service;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private Profile_pic_service pic_service;

    @Value("${profile.pic.dir}")
    private String uploadDirectory;

    @GetMapping("/profile")
    public ModelAndView profilePage(Principal principal){
        ModelAndView model = new ModelAndView();
        Optional<User> optionalUser = Optional.ofNullable(userService.fidUserByEmail(principal.getName()));
        User user = optionalUser.get();
        model.addObject("user", user);
        model.setViewName("user/profile");
        return model;
    }



    //----------------------------------------------

    @GetMapping("/profile_pic_upload")
    public ModelAndView up(Profile_pic profile_pic){
        ModelAndView model = new ModelAndView();
        model.addObject("profile_pic", profile_pic);
        model.setViewName("user/pro_pic_form");
        return model;
    }

    @RequestMapping(value="/profile_pic_upload", method= RequestMethod.POST)
    String fileUploads(Model model, @RequestParam("file") MultipartFile file, Principal principal) {

        try {
            Profile_pic uploadedFile = pic_service.uploadFile(file, uploadDirectory);
            pic_service.save(uploadedFile, principal);
        } catch (InvalidFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/profile";
    }

    @RequestMapping(value="/profile_img", method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<InputStreamResource> uploadedFile(Principal principal) throws IOException {
        Optional<User> optionalUser = Optional.ofNullable(userService.fidUserByEmail(principal.getName()));
        User user = optionalUser.get();
        Path filePath = pic_service.findLastFile(user.getId()).getFilePath();
        return ResponseEntity
                .ok()
                .contentLength(Files.size(filePath))
                .contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(filePath.toString())))
                .body(new InputStreamResource(Files.newInputStream(filePath, StandardOpenOption.READ)));
    }










}
