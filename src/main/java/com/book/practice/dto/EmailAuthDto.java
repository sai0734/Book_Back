package com.book.practice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAuthDto {

    private Long emailNo;

    private String email;

    private String authCode;
}
