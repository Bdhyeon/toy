package com.bdh.toy.book.repository;

import com.bdh.toy.book.dto.GetBookRequest;
import com.bdh.toy.book.entity.Book;
import com.bdh.toy.book.entity.QBook;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    QBook book = QBook.book;

    public List<Book> findFromDbByTitleWriterPublisher(GetBookRequest getBookRequest){
        return jpaQueryFactory.selectFrom(book)
                .where()
                .fetch();
    }

    public Book findFromDbById(long id){
        return jpaQueryFactory.selectFrom(book)
                .where(book.Id.eq(id))
                .fetchOne();
    }

    public void saveAll(List<Book> bookList){
        entityManager.persist(bookList);
    }

    public void save(Book book){
        entityManager.persist(book);
    }
}
