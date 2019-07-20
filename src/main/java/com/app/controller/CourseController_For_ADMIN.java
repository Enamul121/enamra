package com.app.controller;


import com.app.model.Course;
import com.app.model.Section;
import com.app.model.Topic;
import com.app.repository.CourseRepo;
import com.app.repository.SectionRepo;
import com.app.repository.TopicRepo;
import com.app.service.impl.CourseService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/course")
public class CourseController_For_ADMIN {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private SectionRepo sectionRepo;
    @Autowired
    private TopicRepo topicRepo;


    @GetMapping
    public String coursePage(){return "admin/coursePage";}

    @GetMapping("/create")
    public ModelAndView courseFormPagt(){
        ModelAndView model = new ModelAndView("admin/courseForm");
        model.addObject("course", new Course());
        return model;
    }

    @PostMapping("/create")
    public ModelAndView createCourse(@Valid Course course, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();

        if (bindingResult.hasErrors()){
            model.setViewName("admin/courseForm");
            model.addObject("error","Something Went Wrong ......");

        }else {
            courseService.savCourse(course);
            model.addObject("msg","Course created successfully");
            model.setViewName("admin/courseForm");

        }
        return model;
    }

    @GetMapping("/delete_course/{course_id}")
    public ModelAndView deleteCourse(@PathVariable("course_id") Long id){
        courseService.deleteCourse(id);
        return new ModelAndView("redirect:/admin/course/last_10_course");
    }


    @GetMapping("/update_course/{course_id}")
    public ModelAndView updateCourse(@PathVariable("course_id") Long id){
        ModelAndView model = new ModelAndView("admin/update_course");
        Course findCourse = courseService.findCourseById(id);
        model.addObject("course", findCourse);
        return model;
    }

    @PostMapping("/update")
    public ModelAndView update_course(@Valid Course course, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()){
            model.addObject("error","something going wrong");
        }else {
            courseService.savCourse(course);
            model.addObject("msg","Course created successfully");
            model.setViewName("admin/update_course");
        }
        return model;
    }





    @GetMapping("/last_10_course")
    public ModelAndView show_Last_10_course(){
        ModelAndView model = new ModelAndView("admin/course_list_last_10");
        List<Course> courseList = courseService.getLast_10_course();
        model.addObject("courseLists", courseList);
        return model;
    }


    @GetMapping("/course_with_section_last_10")
    public ModelAndView course_with_section(){
        ModelAndView model = new ModelAndView();
        List<Course> course_with_sec = courseRepo.last_10_course_with_section();
        model.addObject("course_w_sec",course_with_sec);
        model.setViewName("admin/course_with_section");
        return model;
    }


    @GetMapping("/single_course_with_all_section/{course_id}")
    public ModelAndView single_course_wit_all_section(@PathVariable("course_id")  Long id){
        ModelAndView model = new ModelAndView("admin/single_course_with_all_sec");
        Course findCourse = courseService.findCourseById(id);
        List<Section> all_sec_for__course = sectionRepo.all_sec_by_course_ID(findCourse.getCourse_id());
        model.addObject("course", findCourse);
        model.addObject("all_sec",all_sec_for__course);
        return model;
    }

// last_10_final
    @GetMapping("/last_10_final")
    public ModelAndView finalcourse(){
        ModelAndView model = new ModelAndView("admin/last_10_final");
        List<Course> courseList = courseService.getLast_10_course();
        model.addObject("courseLists", courseList);
        return model;
    }


    ///admin/course/full_details
    @GetMapping("/full_details/{course_id}")
    public ModelAndView full_details(@PathVariable("course_id") Long id){
        ModelAndView model = new ModelAndView("admin/full_course");
        Course findCourse = courseService.findCourseById(id);
        List<Section> allSection = sectionRepo.all_sec_by_course_ID(findCourse.getCourse_id());
        model.addObject("course", findCourse);
        model.addObject("allSection", allSection);
        return model;
    }


}
