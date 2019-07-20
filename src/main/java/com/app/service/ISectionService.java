package com.app.service;

import com.app.model.Section;

import java.util.List;

public interface ISectionService {


    List<Section> getAllSection();

    Section findSectionByID(Long id);
    void saveSection(Section section, Long course_id); //
    void deleteSection(Long id);






}
