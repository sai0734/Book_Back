package com.book.practice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EMAIL_AUTH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EMAIL_NO")
    private Long emailNo;


    @Column(name="EMAIL")
    private String email;

    @Column(name="AUTH_CODE")
    private String authCode;

    @Column(name="SEND_TIME")
    private LocalDateTime sendTime;

    @PrePersist
    public void prePersist() {
        this.sendTime = LocalDateTime.now();

    }
}
