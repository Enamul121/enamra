package com.app.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;



@Data
@AllArgsConstructor
@ToString(exclude = {"topic","user"})// "topic"
@Entity(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    private String comment_body;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date comment_date;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comments() { }


}
