package com.bdh.toy.book.service;

import com.bdh.toy.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteBookService {

    private final BookRepository bookRepository;

    public int delete(List<Long> idList){
        bookRepository.delete(idList);
        return idList.size();
    }
}
