package com.qualle.truegain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirmation", schema = "public")
public class Confirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    private int code;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private int fails;

    @MapsId
    @OneToOne
    private User user;
}
