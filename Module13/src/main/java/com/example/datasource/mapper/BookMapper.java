package com.example.datasource.mapper;

import com.example.datasource.model.Book;
import com.example.web.dto.BookResponseDto;
import com.example.web.dto.CreateBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "publicYear", source = "publicYear")
    Book toEntity(CreateBookDto dto);

    @Mapping(target = "authorName", source = "author.name")
    BookResponseDto toDto(Book book);


}