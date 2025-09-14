package com.bdh.toy.book.controller;

import com.bdh.toy.book.dto.GetBookListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Value("${book.url}")
    private String bookUrl;

    @GetMapping("/main")
    public String main(){

        return "/book_main";
    }

    public ResponseEntity<List> getBookList(){
        URI uri = UriComponentsBuilder
                .fromUriString(bookUrl)
                .build()
                .toUri();

        GetBookListDTO getBookListDTO = new GetBookListDTO();

        RequestEntity<GetBookListDTO> requestEntity = RequestEntity
                .post(uri)
                .header("header-key", "header-value")
                .body(getBookListDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.exchange(requestEntity, List.class);

        return responseEntity;
    }
}
