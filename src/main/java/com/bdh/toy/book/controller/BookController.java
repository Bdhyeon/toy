package com.bdh.toy.book.controller;

import com.bdh.toy.book.dto.GetBookRequest;
import com.bdh.toy.book.entity.Book;
import com.bdh.toy.book.service.GetBookListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {
    private final GetBookListService  getBookListService;

    @PostMapping("/list")
    public ResponseEntity<List<Book>> getBookList(@RequestBody GetBookRequest getBookRequest){
        List<Book> bookList = getBookListService.getBookList(getBookRequest);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookList);
    }
}
