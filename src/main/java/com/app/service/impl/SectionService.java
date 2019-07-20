package com.app.service.impl;

import com.app.model.Course;
import com.app.model.Section;
import com.app.repository.CourseRepo;
import com.app.repository.SectionRepo;
import com.app.service.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SectionService implements ISectionService {

    @Autowired
    private SectionRepo sectionRepo;
    @Autowired
    private CourseRepo courseRepo;


    @Override
    public List<Section> getAllSection() {
        return sectionRepo.findAll();
    }

    @Override
    public Section findSectionByID(Long id) {
        return sectionRepo.findById(id).get();
    }

    @Override
    public void saveSection(Section section, Long section_id) {

        //
          Course find_Course_for_adding_section = courseRepo.findById(section_id).get();
           section.setSection_id(section_id);
           sectionRepo.save(section);
    }

    @Override
    public void deleteSection(Long id) {
        sectionRepo.deleteById(id);
    }
}
