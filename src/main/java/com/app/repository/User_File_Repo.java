package com.app.repository;

import com.app.model.User_files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface User_File_Repo extends JpaRepository<User_files,Long> {



}
