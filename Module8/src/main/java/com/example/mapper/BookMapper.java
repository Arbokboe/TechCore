package com.example.mapper;

import com.example.domain.*;
import com.example.dto.bookDto.BookResponseDto;
import com.example.dto.bookDto.CreateBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "public_year", source = "public_year")
    Book toEntity(CreateBookDto dto);

    @Mapping(target = "authorName", source = "author.name")
    CreateBookDto toDto(Book book);

    @Mapping(target = "authorName", source = "author.name")
    BookResponseDto toResponseDto(Book book);
}