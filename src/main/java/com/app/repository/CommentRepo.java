package com.app.repository;

import com.app.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepo extends JpaRepository<Comments,Long> {



    @Query(value = "select * from comments where comments.topic_id=? order by comment_id desc  limit 60", nativeQuery = true)
    List<Comments> findAll_by_desc(Long id);
}
