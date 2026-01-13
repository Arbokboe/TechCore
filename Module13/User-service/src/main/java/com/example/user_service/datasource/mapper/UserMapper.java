package com.example.user_service.datasource.mapper;

import com.example.user_service.datasource.model.User;
import com.example.user_service.web.dto.RegisterRequest;
import com.example.user_service.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);

    UserDto toDto(User user);
}
