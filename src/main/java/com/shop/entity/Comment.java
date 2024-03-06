package com.shop.entity;


import com.shop.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
public class Comment extends BaseTimeEntity{


    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String content;
    private String password;
    private Long reCommentID;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_content_id")
    private BoardContent boardContent;




    public static Comment createCommnet(CommentDto commentDto , BoardContent boardContent){
        Comment comment = new Comment();

        comment.setPassword(commentDto.getPassword());
        comment.setContent(commentDto.getContent());
        comment.setName(commentDto.getName());
        comment.setBoardContent(boardContent);
        comment.setReCommentID(commentDto.getReCommentID());

        return comment;
    }




}
