package com.app.service.impl;

import com.app.model.Course;
import com.app.repository.CourseRepo;
import com.app.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepo courseRepo;


    @Override
    public List<Course> findallCourse() {
        return courseRepo.findAll();
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepo.findById(id).get();
    }

    @Override
    public void savCourse(Course course) {
        courseRepo.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }


    @Override
    public List<Course> getLast_10_course() {
        return courseRepo.getLast_10_course();
    }
}
