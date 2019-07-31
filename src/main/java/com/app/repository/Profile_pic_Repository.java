package com.app.repository;

import com.app.model.Profile_pic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Profile_pic_Repository extends JpaRepository<Profile_pic,Long> {


    @Query(value = "select * from profile_picture where user_id=? order by profile_pic_id desc limit 1", nativeQuery = true)
    Profile_pic findByIdDesc(Long id);





  }
