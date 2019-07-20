package com.app.controller;

import com.app.EnamraApplication;
import com.app.model.Course;
import com.app.model.Section;
import com.app.repository.CourseRepo;
import com.app.repository.SectionRepo;
import com.app.service.impl.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnamraApplication.class)
public class CourseControllerTest {


    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private SectionRepo sectionRepo;


    @Test
    @Transactional
    @DirtiesContext
    public void name() {
        Course course = new Course();
        course.setCourse_name("XXL");
        course.setDescription("CCCCCCCCCCCCCCC");
        courseService.savCourse(course);
        log.info("SAVE COURSE -> {}", course);

    }


    @Test
    @Transactional
    public void course_with_section() {
        List<Course> course_with_sec = courseRepo.last_10_course_with_section();
        log.info("Sec with Course -> {}", course_with_sec);
    }

    @Test
    public void sec_with_course() {
        List<Section> pp = sectionRepo.sec_with_course();
        log.info("section with course :: ->{}",pp);
    }



    // ok
    @Test
    public void recently_added_first_four_course() {
        List<Course> last_four = courseRepo.recently_added_first_four_course();
        log.info("Last $$$$$  -> {}", last_four);
    }
}