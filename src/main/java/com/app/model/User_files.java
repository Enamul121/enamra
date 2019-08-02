package com.app.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Data
@NoArgsConstructor
@Entity(name = "user_file")
public class User_files {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_id;

    private String file_name;

    private String file_dir;

    private String full_path;

    private String file_extension;

   @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;






}
