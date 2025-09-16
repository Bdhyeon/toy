package com.bdh.toy.book.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetBookResponse {
    private String key;
    private String srchTarget;
    private String kwd;
    private Integer pageNum;
    private Integer pageSize;
    private String systemType;
    private String category;
    private String sort;
    private String apiType; // xml, json
}
