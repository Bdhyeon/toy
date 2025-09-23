package com.bdh.toy.book.service;

import com.bdh.toy.book.dto.GetBookRequest;
import com.bdh.toy.book.dto.GetBookResponse;
import com.bdh.toy.book.dto.GetBookResult;
import com.bdh.toy.book.entity.Book;
import com.bdh.toy.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetBookListService {

    private final BookRepository bookRepository;

    @Value("${book.url}")
    private String bookUrl;

    @Value("${book.key}")
    private String bookKey;

    public List<Book> getBookList(GetBookRequest getBookRequest){
        List<Book> bookList = new ArrayList<>();
        bookList = bookRepository.findFromDbByTitleWriterPublisher(getBookRequest);

        if (bookList == null) { // db에 데이터 없는 경우
            bookList = findFromWeb(getBookRequest); // web에서 조회
            if (!bookList.isEmpty()) {
                bookRepository.saveAll(bookList);
            }
        }

        return bookList;
    }

    public List<Book> findFromWeb(GetBookRequest getBookRequest){
        List<Book> bookList = new ArrayList<>();
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
        GetBookResponse getBookResponse = responseEntity.getBody();
        for (GetBookResult result : getBookResponse.getResult()) {
            Book book = Book.builder()
                    .title(result.getTitleInfo())
                    .writer(result.getAuthorInfo())
                    .publisher(result.getPubInfo())
                    .build();
            bookList.add(book);
        }

        return bookList;
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
