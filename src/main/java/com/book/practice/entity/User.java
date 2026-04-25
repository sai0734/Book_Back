package com.book.practice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_NO")
    private  Long userNo;

    @Column(nullable = false, unique = true, length = 30, name="ID")
    private String id;

    @Column(nullable = false , name="PASSWORD")
    private String password;

    @Column(nullable = false , name="NAME")
    private String name;

    @Column(nullable = false, name="STATUS")  //0: 정상가입 ,1 :휴면 , 9:탈퇴
    private String status;

    @Column(nullable = false, name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(nullable = false, name="UPDATED_AT")
    private LocalDateTime updatedAt;

    //등록시
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
