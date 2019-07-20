package com.app.repository;

import com.app.model.Course;
import com.app.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {


    @Query(value = "select * from course order by course_id desc limit 10", nativeQuery = true)
    List<Course> getLast_10_course();

    // distinct
    //  select * from course c  left join section s on(c.course_id=s.course_id)  order by c.course_id desc
    @Query(value = "select * from course c  left join section s on(c.course_id=s.course_id)  order by c.course_id desc ", nativeQuery = true)
    List<Course> last_10_course_with_section();

    @Query(value = "select * from course c order by course_id desc limit 0,4", nativeQuery = true)
    List<Course> recently_added_first_four_course();

    @Query(value = "select * from course c order by course_id desc limit 4,4", nativeQuery = true)
    List<Course> recently_added_second_four_course();



}
