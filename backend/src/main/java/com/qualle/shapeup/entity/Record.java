package com.qualle.shapeup.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "record", schema = "public")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int value;
    private String measureType;

    @ManyToOne
    private Workout workout;

    @ManyToOne
    private Exercise exercise;
}
