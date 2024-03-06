package com.shop.controller;


import com.shop.dto.CommentDto;
import com.shop.entity.BoardContent;
import com.shop.entity.Comment;
import com.shop.repository.CommentRepository;
import com.shop.service.BoardService;
import com.shop.service.CommetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {


    private final BoardService boardService;

    private final CommentRepository commentRepository;
    private final CommetService commetService;


    //비밀번호 확인 ajax
    @PostMapping(value = "/freeBoard/commentNew")
    public @ResponseBody String passwordCheck(CommentDto commentDto){

        if(commentDto.getPassword().equals("") || commentDto.getContent().equals("") || commentDto.getName().equals("")){
            return "미입력";
        }

        Optional<BoardContent> boardContent=  boardService.findById(commentDto.getBoardID());
        BoardContent boardContent1 = boardContent.get();
        Comment comment = Comment.createCommnet(commentDto , boardContent1);
        commetService.saveComment(comment);

        return "댓글이 등록되었습니다.";

    }


    //댓글 삭제
    @PostMapping(value = "/freeBoard/deleteComment")
    public @ResponseBody String delete(CommentDto commentDto){

        Long id = commentDto.getBoardID();

        Optional<Comment> comment = commetService.findId(id);

        System.out.println(comment.get().getPassword());
        if(comment.get().getPassword().equals(commentDto.getInputPW())){
            commetService.deleteCommet(id);
            return "삭제완료";
        }
        return "비밀번호";
    }










}
