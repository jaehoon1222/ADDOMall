package com.shop.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentDto {

    private Long boardID;
    private String name;
    private String content;
    private String password;
    private Long reCommentID;
    private String inputPW;

}
