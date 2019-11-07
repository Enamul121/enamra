package com.app.repository;

import com.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepo extends JpaRepository<User,Long> {


    @Query(value = "select *  from user u inner join user_role ur on(u.id=ur.user_id) " +
            "inner join role r on(ur.role_id=r.role_id) where r.role_id=5 ", nativeQuery = true)
    List<User> findAll_ChiefInstructor_ByRoles();


    @Query(value = "select *  from user u inner join user_role ur on(u.id=ur.user_id) " +
            "inner join role r on(ur.role_id=r.role_id) where r.role_id=6 ", nativeQuery = true)
    List<User> findAll_Instructor_ByRoles();



}
