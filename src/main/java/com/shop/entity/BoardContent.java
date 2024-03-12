package com.shop.entity;


import com.shop.dto.BoardContentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "boardContent")
@Getter
@Setter
@ToString
public class BoardContent extends BaseTimeEntity {

    @Id
    @Column(name = "board_content_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String password;
    private String boardName;

    @Column(length = 65000)
    private String content;

    private String regTimeString;

    private String email;
    private String adminCheck;


    public  static  BoardContent createBoardContent(BoardContentDto boardContentDto){
         BoardContent boardContent = new BoardContent();

         boardContent.setName(boardContentDto.getName());
         boardContent.setContent(boardContentDto.getContent());
         boardContent.setPassword(boardContentDto.getPassword());
         boardContent.setBoardName(boardContentDto.getBoardName());
         boardContent.setEmail(boardContentDto.getEmail());
         boardContent.setAdminCheck("0");
         return boardContent;
    }

    public  static  BoardContent createnoticeContent(BoardContentDto boardContentDto){
        BoardContent boardContent = new BoardContent();

        boardContent.setName(boardContentDto.getName());
        boardContent.setContent(boardContentDto.getContent());
        boardContent.setPassword(boardContentDto.getPassword());
        boardContent.setBoardName(boardContentDto.getBoardName());
        boardContent.setEmail(boardContentDto.getEmail());
        boardContent.setAdminCheck("1");
        return boardContent;
    }

}
