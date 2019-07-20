package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "course")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;

    private String course_name;

    private String description;

    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Section> section = new ArrayList<>();


    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
