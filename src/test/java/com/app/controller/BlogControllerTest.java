package com.app.controller;

import com.app.EnamraApplication;
import com.app.model.Blog;
import com.app.repository.BlogRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;




@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnamraApplication.class)
public class BlogControllerTest {


    @Autowired
    private BlogRepo blogRepo;


    @Test
    @Transactional
    public void findPost() {

        List<Blog> list = blogRepo.findPostByCourseId(17l);
        log.info("all post -> {}", list);

    }
}