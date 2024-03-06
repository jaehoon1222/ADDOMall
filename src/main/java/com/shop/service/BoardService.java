package com.shop.service;


import com.shop.dto.BoardContentDto;
import com.shop.dto.BoardSearchDto;
import com.shop.entity.BoardContent;
import com.shop.entity.Comment;
import com.shop.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // 나 서비스다.
@Transactional // 트랜젝션설정 : 성공을하면 그대로 적용 실패하면 롤백
@RequiredArgsConstructor // final 또는 @NonNull 명령어가 붙으면 객체를 자동 붙혀줍니다.
public class BoardService {

    private final BoardRepository boardRepository;
    private final EntityManager em;

    public void saveBoardContent(BoardContent boardContent){

        boardRepository.save(boardContent);
    }
    public void deleteContent(Long id){
        boardRepository.deleteById(id);

    }



    public Optional<BoardContent> findById(Long id){

        return boardRepository.findById(id);
    }


    public List<Comment> commentsList(Long id){

        List<Comment> comments =boardRepository.getComment(id);

        return comments;

    }


    public BoardContent updateBoard (BoardContentDto boardContentDto){
        BoardContent boardContent = em.find(BoardContent.class, boardContentDto.getId());

        boardContent.setBoardName(boardContentDto.getBoardName());
        boardContent.setContent(boardContentDto.getContent());
        boardContent.setName(boardContentDto.getName());
        boardContent.setPassword(boardContentDto.getPassword());
        boardContent.setAdminCheck("0");
        return boardContent;

    }


    public BoardContent updateABoard (BoardContentDto boardContentDto){
        BoardContent boardContent = em.find(BoardContent.class, boardContentDto.getId());

        boardContent.setBoardName(boardContentDto.getBoardName());
        boardContent.setContent(boardContentDto.getContent());
        boardContent.setName(boardContentDto.getName());
        boardContent.setPassword(boardContentDto.getPassword());
        boardContent.setAdminCheck("1");
        return boardContent;

    }




    @Transactional(readOnly = true)
    public Page<BoardContent> getBoardPage(BoardSearchDto boardSearchDto , Pageable pageable){
        return boardRepository.getBoardPage(boardSearchDto , pageable);
    }
    @Transactional(readOnly = true)
    public Page<BoardContent> getABoardPage(BoardSearchDto boardSearchDto , Pageable pageable){
        return boardRepository.getABoardPage(boardSearchDto , pageable);
    }


    @Transactional(readOnly = true)
    public Optional<BoardContent> getBoardView(Long id){

        return boardRepository.findById(id);

    }







}
