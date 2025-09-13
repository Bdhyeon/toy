package com.bdh.toy.dto;

import java.time.LocalDateTime;

public class GetBookListDTO {
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
