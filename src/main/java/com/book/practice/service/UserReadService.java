package com.book.practice.service;

import com.book.practice.Mapper.CommonMapper;
import com.book.practice.dto.ResponseDto;
import com.book.practice.dto.UserDto;
import com.book.practice.dto.UserInsertDto;
import com.book.practice.dto.UserReadBookDto;
import com.book.practice.entity.User;
import com.book.practice.entity.UserReadBook;
import com.book.practice.repository.UserReadRepository;
import com.book.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserReadService {

    private final UserReadRepository repository;
    private final CommonMapper mapper;


    @Transactional
    public ResponseDto<UserReadBookDto> saveUserReadBook(UserReadBookDto dto) {
        if(dto.getUserNo() == null) {
            return ResponseDto.fail("유저 정보가 없습니다.");
        }

        if(dto.getIsbn() == null) {
            return ResponseDto.fail("책에 대한 정보가 없습니다.");
        }

        UserReadBook userBook = mapper.toEntity(dto);

        UserReadBook insertBook = repository.save(userBook);

        return ResponseDto.success(mapper.toDto(insertBook));


    }

    /**
     * 고객별 구독중인 책 가져오기
     * @param dto
     * @return
     */
    public ResponseDto<List<UserReadBookDto>> getListUserReadBook(UserReadBookDto dto) {
        return ResponseDto.success(mapper.toDtoList(repository.findByUserNo(dto.getUserNo())));
    }

    @Transactional
    public ResponseDto<UserReadBookDto> changeMemo(UserReadBookDto dto) {
        UserReadBook urb = repository.findByUserNoAndIsbn(dto.getUserNo(),dto.getIsbn());

        if(urb == null) {
            return ResponseDto.fail("존재하지 않는 데이터입니다.");
        }

        urb.setMemo(dto.getMemo());

        return ResponseDto.success(mapper.toDto(urb));
    }

    @Transactional
    public ResponseDto<UserReadBookDto> changeRating(UserReadBookDto dto) {
        UserReadBook urb = repository.findByUserNoAndIsbn(dto.getUserNo(),dto.getIsbn());

        if(urb == null) {
            return ResponseDto.fail("존재하지 않는 데이터입니다.");
        }

        urb.setRating(dto.getRating());

        return ResponseDto.success(mapper.toDto(urb));
    }

    @Transactional
    public ResponseDto<UserReadBookDto> changeStatus(UserReadBookDto dto) {
        UserReadBook urb = repository.findByUserNoAndIsbn(dto.getUserNo(),dto.getIsbn());

        if(urb == null) {
            return ResponseDto.fail("존재하지 않는 데이터입니다.");
        }

        urb.setStatus(dto.getStatus());

        return ResponseDto.success(mapper.toDto(urb));
    }

    @Transactional
    public ResponseDto<Map<String,Object>> deleteReadBook(UserReadBookDto dto) {
        UserReadBook urb = repository.findByUserNoAndIsbn(dto.getUserNo(),dto.getIsbn());

        Map<String,Object> resultMap = new HashMap<>();
        if(urb == null) {
            return ResponseDto.fail("존재하지 않는 데이터입니다.");
        }

        repository.delete(urb);

        repository.flush();

        resultMap.put("result", "Y");

        return ResponseDto.success(resultMap);
    }


}
