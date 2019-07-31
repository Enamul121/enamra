package com.app.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.file.Path;
import java.nio.file.Paths;


@Data
@NoArgsConstructor
@Entity(name = "profile_picture")
public class Profile_pic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profile_pic_id;

    private String pic_name;

    private String pic_directory;
    @Transient
    private String img_base_name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Profile_pic(String pic_name, String pic_directory, String img_base_name) {
        this.pic_name = pic_name;
        this.pic_directory = pic_directory;
        this.img_base_name = img_base_name;
    }


    public Path getFilePath() {
        if (pic_name == null || pic_directory == null)
            return null;
        return Paths.get(pic_directory, pic_name);
    }

}
