package com.bdh.toy.book.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetBookResponse {
    private Integer total;
    private String kwd;
    private Integer pageNum;
    private Integer pageSize;
    private String category;
    private String sort;
    private List<GetBookResult> result;
}
