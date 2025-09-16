package com.bdh.toy.book.controller;

import com.bdh.toy.book.dto.GetBookListDTO;
import com.bdh.toy.book.dto.GetBookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Value("${book.url}")
    private String bookUrl;

    @Value("${book.key}")
    private String bookKey;

    @GetMapping("/main")
    public String main(){

        return "/book_main";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<GetBookResponse>> getBookList(){
        URI uri = UriComponentsBuilder
                .fromUriString(bookUrl)
                .build()
                .toUri();

        GetBookListDTO getBookListDTO = GetBookListDTO.builder()
                .key(bookKey)
                .pageNum(1)
                .pageSize(10)
                .apiType("json")
                .build();

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri + "?key=" + getBookListDTO.getKey()
                + "&pageNum=" + getBookListDTO.getPageNum()
                + "&apiType=" + getBookListDTO.getApiType()
                + "&pageSize=" + getBookListDTO.getPageSize()
                + "&kwd=" + "1")
                .build();

        log.info("request entity: {}", requestEntity);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GetBookResponse>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<GetBookResponse>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        log.info("responseEntity = {}", responseEntity.getStatusCode());
        log.info("responseEntity.getBody() = {}", responseEntity.getBody());

        return responseEntity;
    }
}
