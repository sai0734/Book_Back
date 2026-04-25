package com.book.practice.controller;

import com.book.practice.dto.ResponseDto;
import com.book.practice.dto.UserReadBookDto;
import com.book.practice.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/readBook")
@RequiredArgsConstructor
public class UserReadBookController {

    private final UserReadService userReadService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto<UserReadBookDto>> insertUserReadBook(@RequestBody(required = false) UserReadBookDto dto) {

        return ResponseEntity.ok(userReadService.saveUserReadBook(dto));

    }

    @PostMapping("/list")
    public ResponseEntity<ResponseDto<List<UserReadBookDto>>> getList(@RequestBody(required = false) UserReadBookDto dto) {

        return ResponseEntity.ok(userReadService.getListUserReadBook(dto));

    }

    @PostMapping("/change/memo")
    public ResponseEntity<ResponseDto<UserReadBookDto>> changeMemo(@RequestBody(required = false) UserReadBookDto dto) {

        return ResponseEntity.ok(userReadService.changeMemo(dto));

    }

    @PostMapping("/change/rating")
    public ResponseEntity<ResponseDto<UserReadBookDto>> changeRating(@RequestBody(required = false) UserReadBookDto dto) {

        return ResponseEntity.ok(userReadService.changeRating(dto));

    }

    @PostMapping("/change/status")
    public ResponseEntity<ResponseDto<UserReadBookDto>> changeStatus(@RequestBody(required = false) UserReadBookDto dto) {

        return ResponseEntity.ok(userReadService.changeStatus(dto));

    }

    @PostMapping("/change/delete")
    public ResponseEntity< ResponseDto<Map<String,Object>> > deleteUserReadBook(@RequestBody(required = false) UserReadBookDto dto) {

        return ResponseEntity.ok(userReadService.deleteReadBook(dto));

    }
}
