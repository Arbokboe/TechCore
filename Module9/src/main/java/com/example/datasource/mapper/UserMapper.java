package com.example.datasource.mapper;


import com.example.datasource.model.User;
import com.example.web.dto.RegisterRequest;
import com.example.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);

    UserDto toDto(User user);
}

