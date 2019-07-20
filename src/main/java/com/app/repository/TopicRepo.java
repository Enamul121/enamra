package com.app.repository;

import com.app.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepo extends JpaRepository<Topic,Long> {


    @Query(value = "select * from topic t where t.section_id=? ", nativeQuery = true)
    List<Topic> all_topic_BY_Section_ID(Long id);

}
