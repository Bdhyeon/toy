package com.bdh.toy.book.controller;

import com.bdh.toy.book.dto.GetBookRequest;
import com.bdh.toy.book.dto.GetBookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
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

    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<GetBookResponse> getBookList(@ModelAttribute GetBookRequest getBookRequest){
        URI uri = UriComponentsBuilder
                .fromUriString(bookUrl)
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri + "?key=" + bookKey
                + "&pageNum=" + getBookRequest.getPageNum()
                + "&apiType=" + getBookRequest.getApiType()
                + "&pageSize=" + getBookRequest.getPageSize()
                + "&kwd=" + getBookRequest.getKwd())
                .build();

        ResponseEntity<GetBookResponse> responseEntity = getRestTemplate().exchange(requestEntity, GetBookResponse.class);

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseEntity.getBody());
    }

    private RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>(converter.getSupportedMediaTypes());
        supportedMediaTypes.add(MediaType.valueOf("text/json"));  // text/json 도 인식 가능하게
        converter.setSupportedMediaTypes(supportedMediaTypes);
        restTemplate.getMessageConverters().add(0, converter); // 우선순위 높게 추가
        return restTemplate;
    }
}
