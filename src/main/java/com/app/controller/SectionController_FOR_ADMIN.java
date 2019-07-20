package com.app.controller;


import com.app.model.Course;
import com.app.model.Section;
import com.app.service.impl.CourseService;
import com.app.service.impl.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/sec")
public class SectionController_FOR_ADMIN {

 // /admin/sec/addSec_for_course/{course_id}
    @Autowired
    private CourseService courseService;

    @Autowired
    private SectionService sectionService;

    @GetMapping("/addSec_for_course")
    public ModelAndView sectionPage(){
        ModelAndView model = new ModelAndView("admin/SectionForm");
        model.addObject("section", new Section());
        return model;
    }

    @PostMapping("/addSec_for_course")
    public ModelAndView secForCourse(@Valid @ModelAttribute("section") Section section, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();

        if (bindingResult.hasErrors()){
            model.addObject("error","something went wrong .........");
            model.setViewName("admin/SectionForm");
        }else {
            Long id_sec = section.getCourse().getCourse_id();
            sectionService.saveSection(section,id_sec); //
            model.addObject("msg","Section/chapter created successfully...");
            model.setViewName("admin/SectionForm");
        }
        return model;
    }

// /admin/sec/delete_sec/{section_id}
    // Delete Section
    @GetMapping("/delete_sec/{section_id}")
    public ModelAndView deleteSection(@PathVariable("section_id") Long id){
        sectionService.deleteSection(id);
        return new ModelAndView("redirect:/admin/course/last_10_course");
    }

    // Update Section
    @GetMapping("/update_sec/{section_id}")
    public ModelAndView updateSection(@PathVariable("section_id") Long id){
        ModelAndView model = new ModelAndView();
        Section section = sectionService.findSectionByID(id);
        model.addObject("section", section);
        model.setViewName("admin/SectionForm");
        return model;

    }



    // /admin/sec/sec_with_last_10_course










}
