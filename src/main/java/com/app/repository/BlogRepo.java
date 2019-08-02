package com.app.repository;

import com.app.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends JpaRepository<Blog,Long> {

//
    @Query(value = "select * from blog b where b.course_id=? order by post_id desc limit 50", nativeQuery = true)
    List<Blog> findPostByCourseId(Long id);

}
