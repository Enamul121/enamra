package com.app.repository;

import com.app.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminRepo")
public interface AdminRepo extends JpaRepository<User,Long> {

    @Query(value = "select *  from user u inner join user_role ur on(u.id=ur.user_id) " +
            "inner join role r on(ur.role_id=r.role_id) where r.role_id=1 ", nativeQuery = true)
    List<User> findAllAdminByRoles();


    @Query(value = "select *  from user u inner join user_role ur on(u.id=ur.user_id) " +
            "inner join role r on(ur.role_id=r.role_id) where r.role_id=3 ", nativeQuery = true)
    List<User> findAllManagerByRoles();


    @Query(value = "select *  from user u inner join user_role ur on(u.id=ur.user_id) " +
            "inner join role r on(ur.role_id=r.role_id) where r.role_id=4 ", nativeQuery = true)
    List<User> findAllHR_By_Roles();

}
