package com.example.repository;


import com.example.datasource.model.Author;
import com.example.datasource.model.Book;
import com.example.datasource.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testFindByPartTitle() {

        Author author = new Author();
        author.setName("Test Author");
        author = entityManager.persistAndFlush(author);


        Book book1 = new Book();
        book1.setTitle("Java Programming");
        book1.setPublic_year(2020);
        book1.setAuthor(author);
        entityManager.persist(book1);

        Book book2 = new Book();
        book2.setTitle("Advanced Java");
        book2.setPublic_year(2022);
        book2.setAuthor(author);
        entityManager.persist(book2);

        Book book3 = new Book();
        book3.setTitle("Python Basics");
        book3.setPublic_year(2021);
        book3.setAuthor(author);
        entityManager.persist(book3);

        entityManager.flush();


        List<Book> javaBooks = bookRepository.findByPartTitle("Java");


        assertEquals(2, javaBooks.size());


        assertEquals("Advanced Java", javaBooks.get(0).getTitle());
        assertEquals(2022, javaBooks.get(0).getPublic_year());

        assertEquals("Java Programming", javaBooks.get(1).getTitle());
        assertEquals(2020, javaBooks.get(1).getPublic_year());

        assertNotNull(javaBooks.get(0).getAuthor());
        assertEquals("Test Author", javaBooks.get(0).getAuthor().getName());
    }
}
