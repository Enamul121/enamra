package com.app.repository;

import com.app.model.Course;
import com.app.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepo extends JpaRepository<Section,Long> {


    @Query(value = "select  * from section left join course on(section.section_id=course.course_id)", nativeQuery = true)
    List<Section> sec_with_course();

    @Query(value = "select * from section s where s.course_id=? ", nativeQuery = true)
    List<Section> all_sec_by_course_ID(Long id);

/*
    @Query(value = "select distinct * from section s order by s.course_id desc ", nativeQuery = true)
    List<Section> sec_w_last_10_cr();*/



}
