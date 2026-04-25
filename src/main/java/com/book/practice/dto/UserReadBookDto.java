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
public class UserReadBookDto {


    private  Long readId;

    private Long userNo;

    private String isbn;

    private String rating;

    private String status;

    private  String memo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
