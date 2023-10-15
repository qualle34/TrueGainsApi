package com.truegain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image", schema = "public")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String link;
    private String name;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<Exercise> exercises;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<Category> categories;
}
