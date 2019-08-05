package com.app.repository;

import com.app.model.User_files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;


@Repository
public interface User_File_Repo extends JpaRepository<User_files,Long> {


    @Query(value = "select  * from user_file where user_id=? AND (file_extension='png' or file_extension='jpg' or " +
            "file_extension='JPEG' or file_extension='jpeg' )  order by file_id desc  limit 60", nativeQuery = true)
    List<User_files> last60Records(Long user_id);



}
