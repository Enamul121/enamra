package com.app.model;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "section_id")
@Entity(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic_name;

    private String video_name;

    private String video_path;

  @OneToMany(mappedBy = "topic",  cascade = CascadeType.REMOVE)
    private Collection<Comments> comments;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section_id;

}
