package com.book.practice.repository;

import com.book.practice.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailAuth,Long> {

}
