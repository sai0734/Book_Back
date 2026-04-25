package com.book.practice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDto {

    private String id;
    private Long userNo;
    private String oldPassword;
    private String password;
    private String name;
    private String status;
    private String confirmPassword;
}
