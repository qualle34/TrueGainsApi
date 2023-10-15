package com.truegain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token", schema = "public")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String access;
    private String refresh;

    @Column(name = "access_expired_at")
    private LocalDateTime accessExpiredAt;

    @Column(name = "refresh_expired_at")
    private LocalDateTime refreshExpiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
