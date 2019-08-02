package com.app.controller;


import com.app.model.Blog;
import com.app.model.Course;
import com.app.model.User;
import com.app.model.User_files;
import com.app.repository.BlogRepo;
import com.app.service.UserService;
import com.app.service.User_File_Service;
import com.app.service.impl.CourseService;
import com.app.service.impl.TopicService;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;





    //  /blog/post_list/__${course_full.course_id
    @GetMapping("/post_list/{course_id}")
    public ModelAndView postList(@PathVariable("course_id") Long id){
        ModelAndView model = new ModelAndView();

        List<Blog> blogList = blogRepo.findPostByCourseId(id);
        model.addObject("postList", blogList);
        model.setViewName("page/post_list");
        return model;


    }




    // /single_post/__${post.post_id

    @GetMapping("/single_post/{post_id}")
    public ModelAndView singlePostPage(@PathVariable("post_id") Long id){
        ModelAndView model = new ModelAndView();
        Blog blog = blogRepo.findById(id).get();
        model.addObject("post", blog);
        model.setViewName("page/single_post");
        return model;
    }



    ///postForm/__${course.course_id /blog/post/create
    @GetMapping("/postForm/{course_id}")
    public ModelAndView blogForm(@PathVariable("course_id") Long id){
        ModelAndView model = new ModelAndView();
        Course findCourse = courseService.findCourseById(id);
        Blog blogPost = new Blog();

        blogPost.setCourse(findCourse);
        model.addObject("blogPost",blogPost );
        model.addObject("courseCC", findCourse);
        model.setViewName("page/blogForm");
        return model;
    }

    @PostMapping("/post/create")
    public ModelAndView createPost(@Valid @ModelAttribute("blogPost") Blog blogPost, BindingResult bindingResult, Principal principal){
        ModelAndView model = new ModelAndView();
        Optional<User> optionalUser = Optional.ofNullable(userService.fidUserByEmail(principal.getName()));


        if (bindingResult.hasErrors()){
            model.addObject("error","something Went Wrong.............");
            model.setViewName("page/blogForm");
        }else {
           blogPost.setUser(optionalUser.get());
            blogRepo.save(blogPost);
            model.addObject("msg","post created successfully......");
            model.setViewName("page/blogForm");
        }
        return model;

    }

    @Autowired
    private User_File_Service fileService;

// /blog/file_upload

    @GetMapping("/file_upload")
    public ModelAndView fileUpload(){
        ModelAndView model =new ModelAndView();
        model.addObject("user_file", new User_files());
        model.setViewName("page/file_upload");
        return model;
    }



    @PostMapping("/file_upload")
    public ModelAndView uploadFile(@Valid @ModelAttribute User_files  user_file,  BindingResult bindingResult,
                                   Principal principal,MultipartFile file){
        Optional<User> optionalUser = Optional.ofNullable(userService.fidUserByEmail(principal.getName()));
        ModelAndView model =new ModelAndView();
        if (bindingResult.hasErrors()){

        }else { user_file.setUser(optionalUser.get());
            fileService.saveFile(user_file,file);
        }

        model.setViewName("redirect:/profile");
        return model;


    }


























}
