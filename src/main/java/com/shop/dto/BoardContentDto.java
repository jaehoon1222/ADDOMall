package com.shop.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardContentDto {

    Long id;


    private LocalDateTime regTime;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotBlank(message = "게시글 내용은 필수 입력 값입니다.")
    private String content;

    @NotBlank(message = "게시글 제목은 필수 입력값입니다.")
    private String boardName;

    private String email;

    private String adminCheck;





}
