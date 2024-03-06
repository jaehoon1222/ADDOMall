package com.shop.dto;


import lombok.Getter;
import lombok.Setter;


//상품 조회 조건 DTO
@Getter @Setter
public class BoardSearchDto {

    private String searchType;  //조회 날짜\
    private String searchBy;    //조회 유형
    private String searchQuery="";  //검색단어

}
