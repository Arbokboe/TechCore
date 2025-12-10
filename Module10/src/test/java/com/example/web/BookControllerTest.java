package com.example.web;

import com.example.datasource.mapper.BookMapper;
import com.example.di.jwt.JwtTokenProvider;
import com.example.web.controller.BookController;
import com.example.web.dto.BookResponseDto;
import com.example.web.dto.CreateBookDto;
import com.example.web.service.AsyncService;
import com.example.web.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@TestPropertySource(properties = {
        "spring.autoconfigure.exclude=" +
                "org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
})
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private AsyncService asyncService;

    @MockitoBean
    private BookMapper bookMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindBookById() throws Exception {


        BookResponseDto Book = new BookResponseDto("Test book", 2025, "Test Author");


        when(bookService.findBookById(1L)).thenReturn(Book);

        mockMvc.perform(get("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test book"))
                .andExpect(jsonPath("$.authorName").value("Test Author"));
    }

    @Test
    public void testCreateBook_Success() throws Exception {

        CreateBookDto createBookDto = new CreateBookDto();
        createBookDto.setTitle("New Book");
        createBookDto.setAuthorName("New Author");
        createBookDto.setPublicYear(2024);

        BookResponseDto responseDto = new BookResponseDto("New Book", 2024, "New Author");

        when(bookService.saveBook(any(CreateBookDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/book/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBookDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.authorName").value("New Author"))
                .andExpect(jsonPath("$.public_year").value(2024));
    }


    @Test
    public void testCreateBook_ValidationError() throws Exception {

        CreateBookDto Dto = new CreateBookDto();
        Dto.setTitle("");
        Dto.setAuthorName("Author");
        Dto.setPublicYear(2024);

        mockMvc.perform(post("/api/book/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Dto)))
                .andExpect(status().isBadRequest());
    }
}