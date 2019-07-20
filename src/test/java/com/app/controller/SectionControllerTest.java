package com.app.controller;

import com.app.EnamraApplication;
import com.app.model.Section;
import com.app.repository.SectionRepo;
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
public class SectionControllerTest {


    @Autowired
    private SectionRepo sectionRepo;


    @Test
    public void single_course_with_all_section() {
        List<Section> list = sectionRepo.all_sec_by_course_ID(11l);
        log.info("Section For Single Course -> {}", list);
    }


   /* @Test
    public void section_with_last_10_course_desc() {
        List<Section> sectionList = sectionRepo.sec_w_last_10_cr();
        log.info("PPxxxxx -> {}",sectionList);
    }*/
}