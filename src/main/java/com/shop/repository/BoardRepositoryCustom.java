package com.shop.repository;

import com.shop.dto.BoardSearchDto;
import com.shop.entity.BoardContent;
import com.shop.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    Page<BoardContent> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable);
    Page<BoardContent> getABoardPage(BoardSearchDto boardSearchDto, Pageable pageable);
    List<Comment> getComment(Long id);
}
