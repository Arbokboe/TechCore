package com.example.mapper;


import com.example.domain.User;
import com.example.dto.RegisterRequest;
import com.example.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);

    UserDto toDto(User user);
}

