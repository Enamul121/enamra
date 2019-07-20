package com.app.service;

import com.app.model.Topic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITopicService {

    List<Topic> getAllTopic();

    Topic findTopicByID(Long id);

    void saveTopic(Topic topic, MultipartFile file);

    void deleteTopicByID(Long id);




}
