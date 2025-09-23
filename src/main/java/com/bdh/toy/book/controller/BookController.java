package com.bdh.toy.book.controller;

import com.bdh.toy.book.dto.GetBookRequest;
import com.bdh.toy.book.entity.Book;
import com.bdh.toy.book.service.GetBookListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {
    private final GetBookListService  getBookListService;

    @GetMapping("/main")
    public String main(){
        return "/book_main";
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<List<Book>> getBookList(@ModelAttribute GetBookRequest getBookRequest){
        List<Book> bookList = getBookListService.getBookList(getBookRequest);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookList);
    }

}
