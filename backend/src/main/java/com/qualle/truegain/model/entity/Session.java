package com.qualle.truegain.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "session", schema = "public")
public class Session {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "expired_at")
    private Long expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
