package com.shop.service;


import com.shop.entity.Comment;
import com.shop.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service // 나 서비스다.
@Transactional // 트랜젝션설정 : 성공을하면 그대로 적용 실패하면 롤백
@RequiredArgsConstructor // final 또는 @NonNull 명령어가 붙으면 객체를 자동 붙혀줍니다.
public class CommetService {

    private final CommentRepository commentRepository;


    public Optional<Comment> findId(Long id){

        return commentRepository.findById(id);
    }

    public void deleteCommet(Long id){
        commentRepository.deleteById(id);
    }
    public void saveComment(Comment comment){

        commentRepository.save(comment);
    }


}
