package com.bdh.toy.book.repository;

import com.bdh.toy.book.dto.GetBookRequest;
import com.bdh.toy.book.entity.Book;
import com.bdh.toy.book.entity.QBook;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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
                .where(book.title.contains(getBookRequest.getKwd())
                        .or(book.publisher.contains(getBookRequest.getKwd())
                        .or(book.writer.contains(getBookRequest.getKwd()))))
                .fetch();
    }

    public Book findFromDbById(long id){
        return jpaQueryFactory.selectFrom(book)
                .where(book.Id.eq(id))
                .fetchOne();
    }

    @Transactional
    public void saveAll(List<Book> bookList){
        for (Book book : bookList) {
            entityManager.persist(book);
        }
    }

    @Transactional
    public void save(Book book){
        entityManager.persist(book);
    }

    @Transactional
    public void delete(List<Long> idList) {
        jpaQueryFactory.delete(book)
                .where(book.Id.in(idList))
                .execute();
    }
}
