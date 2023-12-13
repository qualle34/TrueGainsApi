package com.qualle.truegain.model.entity;

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
    private float weight;
    private int reps;

    @ManyToOne(fetch = FetchType.LAZY)
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;
}
