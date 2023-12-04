package com.qualle.truegain.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "settings", schema = "public")
public class Settings {

    @Id
    @Column(name = "user_id")
    private long userId;

    private String language;
    private String units;
}
