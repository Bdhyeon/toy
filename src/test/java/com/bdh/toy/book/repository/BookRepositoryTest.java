package com.bdh.toy.book.repository;

import com.bdh.toy.book.entity.Book;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Book savedBook = bookRepository.save(book);

        //then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getWriter()).isEqualTo(book.getWriter());
        assertThat(savedBook.getPublisher()).isEqualTo(book.getPublisher());
        assertThat(savedBook.getPublishedAt()).isEqualTo(book.getPublishedAt());
    }

    @Test
    @Order(2)
    public void saveAll(){
        //given
        ArrayList<Book> books = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Book book = Book.builder()
                    .title("title"+i)
                    .writer("writer"+i)
                    .publisher("publisher"+i)
                    .publishedAt(LocalDate.now())
                    .build();
            books.add(book);
        }

        //when
        bookRepository.saveAll(books);

        //then
        List<Book> savedBooks = bookRepository.findAll();
        for (Book book : savedBooks) {
            assertThat(book).isNotNull();
            assertThat(book.getTitle()).isNotNull();
            assertThat(book.getWriter()).isNotNull();
            assertThat(book.getPublisher()).isNotNull();
            assertThat(book.getPublishedAt()).isNotNull();
        }
    }

    @Test
    @Order(3)
    public void findAll(){
        // given
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Book book = Book.builder()
                    .title("title"+i)
                    .writer("writer"+i)
                    .publisher("publisher"+i)
                    .publishedAt(LocalDate.now())
                    .build();
            books.add(book);
        }
        bookRepository.saveAll(books);

        //when
        List<Book> savedBooks = bookRepository.findAll();

        //then
        assertThat(savedBooks).isNotNull();
        assertThat(savedBooks.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void findById(){
        //given
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Book book = Book.builder()
                    .title("title"+i)
                    .writer("writer"+i)
                    .publisher("publisher"+i)
                    .publishedAt(LocalDate.now())
                    .build();
            books.add(book);
        }
        bookRepository.saveAll(books);

        long min = bookRepository.findAll().get(0).getId();
        long max =  bookRepository.findAll().get(books.size()-1).getId();

        // when
        Optional<Book> book =  bookRepository.findById(min);

        //then
        assertThat(book.isPresent()).isTrue();
        assertThat(book.get().getId().equals(min)).isTrue();
        assertThat(book.get().getTitle()).isNotNull();
        assertThat(book.get().getWriter()).isNotNull();
        assertThat(book.get().getPublisher()).isNotNull();
        assertThat(book.get().getPublishedAt()).isNotNull();

    }
}
