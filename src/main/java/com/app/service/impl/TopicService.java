package com.app.service.impl;

import com.app.model.Topic;
import com.app.repository.TopicRepo;
import com.app.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class TopicService implements ITopicService {

    @Autowired
    private TopicRepo topicRepo;

    private String file_uploads_dir = "/home/enamul/IdeaProjects/new/enamra/src/main/resources/static/upload_files";

    @Override
    public List<Topic> getAllTopic() {
        return topicRepo.findAll();
    }

    @Override
    public Topic findTopicByID(Long id) {
        return topicRepo.findById(id).get();
    }

    @Override
    public void saveTopic(Topic topic, MultipartFile file) {

        String file_Name = file.getOriginalFilename();

        String fileName = file_Name.replace(" ", "_").trim();
        String modifyFile = fileName.substring(0,fileName.lastIndexOf("."))+"_"+
                System.nanoTime()+fileName.substring(fileName.lastIndexOf("."));

        fileStore(file,modifyFile);
        topic.setVideo_name(file_Name);
        topic.setVideo_path("/upload_files/"+modifyFile);
        topicRepo.save(topic);
    }

    @Override
    public void deleteTopicByID(Long id) {
        Topic topic = findTopicByID(id);
        File file = new File(file_uploads_dir+"/"+ topic.getVideo_path());
        file.delete();
        topicRepo.deleteById(id);
    }


    public void fileStore(MultipartFile file, String fileName){
        try(InputStream in =file.getInputStream()) {
            Files.copy(in, Paths.get(file_uploads_dir+"/"+fileName), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){e.printStackTrace();}

    }

}
