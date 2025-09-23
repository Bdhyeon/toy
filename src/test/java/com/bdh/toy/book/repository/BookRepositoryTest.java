package com.bdh.toy.book.repository;

import com.bdh.toy.book.entity.Book;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @DisplayName("도서 저장 성공")
    @Test
    @Order(1)
    public void save() {
        //given
        Book book = Book.builder().title("자몽 살구 클럽")
                .writer("한로로")
                .publisher("어센틱")
                .publishedAt(LocalDate.of(2025, 7, 25))
                .build();

        //when
        bookRepository.save(book);
        Book savedBook = bookRepository.findFromDbById(book.getId());

        //then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getWriter()).isEqualTo(book.getWriter());
        assertThat(savedBook.getPublisher()).isEqualTo(book.getPublisher());
        assertThat(savedBook.getPublishedAt()).isEqualTo(book.getPublishedAt());
    }

}
