package com.book.practice.Mapper;

import com.book.practice.dto.UserDto;
import com.book.practice.dto.UserInsertDto;
import com.book.practice.dto.UserReadBookDto;
import com.book.practice.entity.User;
import com.book.practice.entity.UserReadBook;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommonMapper {
    UserDto toDto(User user);

    UserReadBookDto toDto(UserReadBook userReadBook);

    UserReadBook toEntity(UserReadBookDto dto);

    User toEntity(UserInsertDto userDto);

    List<UserReadBookDto> toDtoList(List<UserReadBook> userReadBookList);
}
