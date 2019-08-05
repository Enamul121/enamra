package com.app.controller;

import com.app.EnamraApplication;
import com.app.model.Pager;
import com.app.model.User_files;
import com.app.repository.User_File_Repo;
import com.app.service.User_File_Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;



@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnamraApplication.class)
public class User_FilesControllerTest {

    @Autowired
    private User_File_Repo fileRepo;

    @Autowired
    private User_File_Service fileService;


    @Test
    public void viewIMG() {
        List<User_files> user_files = fileRepo.findAll();
        log.info("All Files -> {}", user_files);
    }

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int START_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 3;
    private static final int[] PAGE_SIZES = {3, 5, 10, 20};


    @Test
    @Transactional
    public void pagenation() {

/*
        int entityRecordPerPage = pageSize.orElse(INITIAL_PAGE_SIZE);
        int pageSL = (pageSerial.orElse(0)<1)?START_PAGE:pageSerial.get()-1;*/

        Page<User_files> studentPage = fileRepo.findAll(PageRequest.of(0,5, Sort.by("file_id").descending()));
        Pager pager = new Pager(studentPage.getTotalPages(),studentPage.getNumber(), BUTTONS_TO_SHOW);


        log.info("List : -> {}",studentPage);





    }
}