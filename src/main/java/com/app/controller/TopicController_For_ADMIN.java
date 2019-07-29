package com.app.controller;


import com.app.model.Comments;
import com.app.model.Course;
import com.app.model.Section;
import com.app.model.Topic;
import com.app.repository.CommentRepo;
import com.app.repository.SectionRepo;
import com.app.repository.TopicRepo;
import com.app.service.ICourseService;
import com.app.service.ISectionService;
import com.app.service.ITopicService;
import com.app.service.impl.TopicService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/topic")
public class TopicController_For_ADMIN {

     @Autowired
    private ITopicService topicService;
     @Autowired
     private ICourseService courseService;
     @Autowired
     private ISectionService sectionService;
     @Autowired
     private SectionRepo sectionRepo;
     @Autowired
     private TopicRepo topicRepo;
     @Autowired
     private CommentRepo commentRepo;


    @GetMapping("/addTopic_for_section")
    public ModelAndView addTopicPage(){
        ModelAndView model = new ModelAndView("admin/topicForm");
        model.addObject("topic", new Topic());
        return model;
    }

    @PostMapping("/addTopic_for_section")
    public ModelAndView addTopicForSEction(@Valid Topic topic, BindingResult bindingResult, MultipartFile file){
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()){
            model.addObject("error","Something Went Wrong");
            model.setViewName("admin/topicForm");
        }else {
            if (file==null){

            }

            topicService.saveTopic(topic,file);
            model.addObject("msg"," Topic Created Successfully");
            model.setViewName("admin/topicForm");
        }

        return model;
    }


    // /admin/topic/see_all_topic_for_this_section/{section_id}
    @GetMapping("/see_all_topic_for_this_section/{section_id}")
    public ModelAndView see_topic_for_section(@PathVariable("section_id") Long id){
        ModelAndView model = new ModelAndView();
        Section findSection = sectionService.findSectionByID(id);
        Course findCourseForSection = courseService.findCourseById(findSection.getCourse().getCourse_id());
        List<Topic> topicList =   topicRepo.all_topic_BY_Section_ID(id);  //topicService.getAllTopic();
        model.addObject("course", findCourseForSection);
        model.addObject("section",findSection);
        model.addObject("topic", topicList);
        model.setViewName("admin/single_section_with_all_topic");
        return model;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteTopic(@PathVariable("id") Long id){
        topicService.deleteTopicByID(id);
        return new ModelAndView("redirect:/admin/course");
    }

// /admin/topic/update/{id}
    @GetMapping("/update/{id}")
    public ModelAndView updateTopic(@PathVariable("id") Long id){
        ModelAndView model = new ModelAndView("admin/topicForm");
        Topic topic = topicService.findTopicByID(id);
        model.addObject("topic", topic);

        return model;

    }

    // /admin/topic/all_topic/{section_id}


    @GetMapping("/all_topic/{section_id}")
    public ModelAndView allTopic(@PathVariable("section_id") Long id){
        ModelAndView model = new ModelAndView("admin/all_topic");
        Section findSection = sectionService.findSectionByID(id);
        Course findCourse = courseService.findCourseById(findSection.getCourse().getCourse_id());
        List<Topic> topicList = topicRepo.all_topic_BY_Section_ID(findSection.getSection_id());

        model.addObject("course", findCourse);
        model.addObject("section", findSection);
        model.addObject("topicList", topicList);
        return model;
    }


    /// single_topic






    @GetMapping("/single_topic/{id}")
    public ModelAndView singleTopic(@PathVariable("id") Long id){
        ModelAndView model = new ModelAndView("admin/single_topic");
        Topic findTopic = topicService.findTopicByID(id);
        List<Comments> commentsList = commentRepo.findAll_by_desc(id);
        Section findSection = sectionService.findSectionByID(findTopic.getSection_id().getSection_id());
        Course findCourse = courseService.findCourseById(findSection.getCourse().getCourse_id());
        List<Topic> topicList = topicRepo.all_topic_BY_Section_ID(findSection.getSection_id());
        model.addObject("course1", findCourse);
        model.addObject("section1", findSection);
        model.addObject("topicList1", topicList);
        model.addObject("topic_single", findTopic);
        model.addObject("commentList", commentsList);
        return model;
    }







   }
