package com.app.service;

import com.app.model.Course;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseService {

    List<Course> findallCourse();

    Course findCourseById(Long id);

    void  savCourse(Course course);

    void deleteCourse(Long id);

    List<Course> getLast_10_course();



}
