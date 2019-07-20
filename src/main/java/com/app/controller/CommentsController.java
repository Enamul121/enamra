package com.app.controller;


import com.app.model.Comments;
import com.app.model.Topic;
import com.app.model.User;
import com.app.service.UserService;
import com.app.service.impl.CommentsService;
import com.app.service.impl.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.PipedReader;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/comment")
public class CommentsController {


    @Autowired
    private CommentsService commentsService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ModelAndView commentPage(@PathVariable("id") Long id, Principal principal){
        ModelAndView model = new ModelAndView("user/commentForm");
        Comments comments = new Comments();
        Topic findtopic = topicService.findTopicByID(id);
        Optional<User> optionalUser = Optional.ofNullable(userService.fidUserByEmail(principal.getName()));
        comments.setUser(optionalUser.get());
        comments.setTopic(findtopic);
        model.addObject("comments", comments);
        model.addObject("topicCC", findtopic);
        return model;
    }


    @PostMapping("/create")
    public ModelAndView createComment(@Valid Comments comments, BindingResult bindingResult, Principal principal){
        ModelAndView model = new ModelAndView();
       Optional<User> optionalUser = Optional.ofNullable(userService.fidUserByEmail(principal.getName()));


       if (bindingResult.hasErrors()){
            model.setViewName("user/commentForm");
        }else {
           comments.setUser(optionalUser.get());
           comments.setTopic(comments.getTopic());
          commentsService.saveComment(comments);
               model.setViewName("redirect:/g/topic/single_topic/"+ comments.getTopic().getId());
         //  model.setViewName("redirect:/");
        }


        return model;
    }





 }
