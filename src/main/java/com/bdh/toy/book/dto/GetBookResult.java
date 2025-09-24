package com.bdh.toy.book.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetBookResult {
    private String titleInfo;
    private String typeName;
    private String placeInfo;
    private String authorInfo;
    private String pubInfo;
    private String pubYearInfo;
    private String regData;
    private String isbn;
    private String imageUrl;
}
