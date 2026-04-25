package com.book.practice.repository;

import com.book.practice.entity.UserReadBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReadRepository extends JpaRepository<UserReadBook, Long> {

    List<UserReadBook> findByUserNo(Long userNo);
    UserReadBook findByUserNoAndIsbn(Long userNo,String isbn);
    void deleteByUserNoAndIsbn(String userNo,String isbn);
}
