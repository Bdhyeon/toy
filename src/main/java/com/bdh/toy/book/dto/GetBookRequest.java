package com.bdh.toy.book.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetBookRequest {
    private final String CATEGORY = "도서";
    private final String API_TYPE = "json"; // xml, json

    private String key;
    private String srchTarget;
    private String kwd;
    private Integer pageNum;
    private Integer pageSize;
    private String systemType;
    private String sort;
}
