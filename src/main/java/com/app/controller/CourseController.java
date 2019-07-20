package com.app.controller;


import com.app.model.Course;
import com.app.model.Section;
import com.app.model.Topic;
import com.app.repository.SectionRepo;
import com.app.repository.TopicRepo;
import com.app.service.impl.CourseService;
import com.app.service.impl.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/v/course")
public class CourseController {

 //  /v/course/full/{course_id}
    @Autowired
    private CourseService courseService;
    @Autowired
    private SectionRepo sectionRepo;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private TopicRepo topicRepo;


    @GetMapping("/full/{course_id}")
    public ModelAndView fullCourse(@PathVariable("course_id") Long id){
        ModelAndView model = new ModelAndView("page/course_full");
        Course course = courseService.findCourseById(id);
        List<Section> allSection = sectionRepo.all_sec_by_course_ID(course.getCourse_id());
        model.addObject("course_full", course);
        model.addObject("section", allSection);
        return model;
    }



    @GetMapping("/all_topic/{section_id}")
    public ModelAndView allTopic(@PathVariable("section_id") Long id){
        ModelAndView model = new ModelAndView("page/full_topic");
        Section findSection = sectionService.findSectionByID(id);
        Course findCourse = courseService.findCourseById(findSection.getCourse().getCourse_id());
        List<Topic> topicList = topicRepo.all_topic_BY_Section_ID(findSection.getSection_id());

        model.addObject("course1", findCourse);
        model.addObject("section1", findSection);
        model.addObject("topicList1", topicList);
        return model;
    }



}
