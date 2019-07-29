package com.app.controller;


import com.app.model.Blog;
import com.app.model.Course;
import com.app.model.User;
import com.app.repository.BlogRepo;
import com.app.service.UserService;
import com.app.service.impl.CourseService;
import com.app.service.impl.TopicService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

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





}
