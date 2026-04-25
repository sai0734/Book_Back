package com.book.practice.controller;

import com.book.practice.dto.EmailAuthDto;
import com.book.practice.dto.ResponseDto;
import com.book.practice.dto.UserDto;
import com.book.practice.dto.UserInsertDto;
import com.book.practice.service.MailService;
import com.book.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    @PostMapping("/checkId")
    public ResponseEntity<ResponseDto<Map<String,Object>>> checkUserIdDup(@RequestBody(required = false)UserInsertDto dto) {

        ResponseDto<Map<String,Object>> resDto = new ResponseDto<>();

        return ResponseEntity.ok(userService.getCheckId(dto.getId()));

    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<UserDto>> saveUser(@RequestBody(required = false)UserInsertDto dto) {

        return ResponseEntity.ok(userService.saveUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<UserDto>> loginUser(@RequestBody(required = false)UserInsertDto dto) {

        return ResponseEntity.ok(userService.loginUser(dto));
    }

    @PostMapping("/change")
    public ResponseEntity<ResponseDto<UserDto>> changeUser(@RequestBody(required = false)UserInsertDto dto) {

        return ResponseEntity.ok(userService.changeUser(dto));
    }

    @PostMapping("/sendEmail")
    public ResponseEntity sendEmail(@RequestBody(required = false)UserInsertDto dto) {

        return ResponseEntity.ok(mailService.send(dto.getEmail()));
    }

    @PostMapping("/confirmAuthCode")
    public ResponseEntity confirmAuthCode(@RequestBody(required = false) EmailAuthDto dto) {

        return ResponseEntity.ok(mailService.confirmAuthCode(dto));
    }
}
