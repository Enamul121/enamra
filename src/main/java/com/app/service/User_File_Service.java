package com.app.service;


import com.app.model.User;
import com.app.model.User_files;
import com.app.repository.User_File_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

@Service
public class User_File_Service {


    @Autowired
    private UserService userService;

    @Autowired
    private User_File_Repo file_repo;

   // @Value("user.file.dir")
    String user_file_dir = "/home/enamul/IdeaProjects/new/enamra/src/main/resources/static/user_files";

    //   Upload & then store it to the server.
    public void fileStore(MultipartFile file, String modifiedFileName){
        try (InputStream in = file.getInputStream()){
            Files.copy(in, Paths.get(this.user_file_dir + "/" + modifiedFileName), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){e.printStackTrace();}

    }



    public void saveFile(User_files userFiles, MultipartFile file) {
        String file_name = file.getOriginalFilename();
        String fileName = file_name.replace(" ","_").trim();
        String file_extension = fileName.substring(fileName.lastIndexOf(".")+1);
        String modifiedName = fileName.substring(0,fileName.lastIndexOf("."))+"_" + System.nanoTime() +"_"+
                fileName.substring(fileName.lastIndexOf("."));

        fileStore(file, modifiedName);
        userFiles.setFile_name(modifiedName);
        userFiles.setFile_dir(user_file_dir+"/"+modifiedName);
        userFiles.setFile_extension(file_extension);
        userFiles.setFull_path("http://localhost:8080/user_files/"+modifiedName);
        file_repo.save(userFiles);

    }




}
