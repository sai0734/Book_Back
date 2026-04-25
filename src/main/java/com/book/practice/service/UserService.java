package com.book.practice.service;

import com.book.practice.Mapper.CommonMapper;
import com.book.practice.dto.ResponseDto;
import com.book.practice.dto.UserDto;
import com.book.practice.dto.UserInsertDto;
import com.book.practice.entity.User;
import com.book.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final CommonMapper mapper;

    public ResponseDto<Map<String,Object>> getCheckId(String id) {

        Map<String,Object> resultMap = new HashMap<>();

        String yn = repository.existsById(id) ? "N" : "Y";

        resultMap.put("result",yn);

        return ResponseDto.success(resultMap);
    }

    public ResponseDto<UserDto> saveUser(UserInsertDto dto) {

        if(!StringUtils.hasText(dto.getId())) {
            return ResponseDto.fail("ID를 입력하지 않았습니다.");
        }

        if(!dto.getPassword().equals(dto.getConfirmPassword())) {
            return ResponseDto.fail("입력한 비밀번호가 서로 다릅니다.");
        }

        if(!StringUtils.hasText(dto.getName())) {
            return ResponseDto.fail("이름이 필수입니다.");
        }

        dto.setStatus("0");

        User user = mapper.toEntity(dto);

        User savedUser = repository.save(user);

        repository.flush();


        return ResponseDto.success(mapper.toDto(savedUser));

    }

    public ResponseDto<UserDto> loginUser(UserInsertDto dto) {

        User loginUser = repository.findById(dto.getId()).orElse(null);

        if(loginUser == null || !loginUser.getPassword().equals(dto.getPassword())) {
            return ResponseDto.fail("존재하지 않는 아이디거나 비밀번호가 틀립니다.");
        }

        return ResponseDto.success(mapper.toDto(loginUser));
    }

    @Transactional
    public ResponseDto<UserDto> changeUser(UserInsertDto dto) {

        User loginUser = repository.findById(dto.getUserNo()).orElse(null);

        if(loginUser == null || !loginUser.getPassword().equals(dto.getPassword())) {
            return ResponseDto.fail("존재하지 않는 계정입니다.");
        }

        if(dto.getOldPassword() != null || !dto.getOldPassword().equals(loginUser.getPassword())) {
            return ResponseDto.fail("비밀번호 인증에 실패하였습니다.");
        } else {
            if(dto.getPassword().equals(dto.getConfirmPassword())) {
                return ResponseDto.fail("입력한 비밀번호와 비밀번호 확인이 서로 다릅니다.");
            }
        }

        loginUser.setPassword(dto.getPassword());

        if(dto.getName() != null) {
            loginUser.setName(dto.getName());
        }

        return ResponseDto.success(mapper.toDto(loginUser));
    }
}
