package com.app.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@Entity(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Size(min = 12, max = 250, message = "Title must be 12 to 250 character")
    private String post_title;

    @Size(min = 20, max = 6500, message = "post range 20 to 6500 character")
    private String post_body;






}
