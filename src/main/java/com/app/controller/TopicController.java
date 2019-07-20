package com.app.controller;


import com.app.model.Comments;
import com.app.model.Course;
import com.app.model.Section;
import com.app.model.Topic;
import com.app.repository.CommentRepo;
import com.app.repository.TopicRepo;
import com.app.service.impl.CourseService;
import com.app.service.impl.SectionService;
import com.app.service.impl.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/g/topic")
public class TopicController {


    @Autowired
    private TopicService topicService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private TopicRepo topicRepo;
    @Autowired
    private CommentRepo commentRepo;


    /// single_topic

    @GetMapping("/single_topic/{id}")
    public ModelAndView singleTopic(@PathVariable("id") Long id){
        ModelAndView model = new ModelAndView("page/single_topic");
        Topic findTopic1 = topicService.findTopicByID(id);
        List<Comments> commentsList = commentRepo.findAll_by_desc(id);
        Section findSection1 = sectionService.findSectionByID(findTopic1.getSection_id().getSection_id());
        Course findCourse = courseService.findCourseById(findSection1.getCourse().getCourse_id());
        List<Topic> topicList = topicRepo.all_topic_BY_Section_ID(findSection1.getSection_id());
        model.addObject("course", findCourse);
        model.addObject("section", findSection1);
        model.addObject("topicList", topicList);
        model.addObject("topic_single", findTopic1);
        model.addObject("commentsList", commentsList);
        return model;
    }






}
