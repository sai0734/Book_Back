package com.book.practice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER_READ_BOOK",
        uniqueConstraints =  {
            @UniqueConstraint(
                    name ="UK_USER_READ_BOOK",
                    columnNames = {"USER_NO" , "ISBN"}
            )
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReadBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="READ_ID")
    private  Long readId;

    @Column(nullable = false, name="USER_NO")   //유저NO
    private Long userNo;

    @Column(nullable = false , name="ISBN")     //BOOK 고유번호
    private String isbn;

    @Column(nullable = true , name="RATING")   //점수
    private String rating;

    @Column(nullable = true, name="STATUS")  //0: 등록(읽기전) , 1:읽는중 , 2:다 읽음
    private String status;

    @Column(nullable = true)                //메모
    private  String memo;

    @Column(nullable = false, name="CREATED_AT")        //등록일
    private LocalDateTime createdAt;

    @Column(nullable = false, name="UPDATED_AT")        //수정일
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
