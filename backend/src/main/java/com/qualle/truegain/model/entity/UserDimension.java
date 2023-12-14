package com.qualle.truegain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_dimension", schema = "public")
public class UserDimension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private OffsetDateTime date;

    private float value;

    @ManyToOne
    private Dimension dimension;

    @ManyToOne
    private User user;
}
