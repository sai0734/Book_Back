package com.book.practice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {

    private  Long userNo;

    private String name;

     //0: 정상가입 ,1 :휴면 , 9:탈퇴
    private String status;

    //등록일
    private LocalDateTime createdAt;

    //수정일
    private LocalDateTime updatedAt;

}
