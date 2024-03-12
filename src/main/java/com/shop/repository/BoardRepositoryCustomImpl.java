package com.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.dto.BoardSearchDto;
import com.shop.entity.BoardContent;
import com.shop.entity.Comment;
import com.shop.entity.QBoardContent;
import com.shop.entity.QComment;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.util.List;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {


    private JPAQueryFactory queryFactory;


    public BoardRepositoryCustomImpl(EntityManager em){
        this.queryFactory= new JPAQueryFactory(em);
    }

    @Override
    public Page<BoardContent> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable) {

        QueryResults<BoardContent> results = queryFactory
                .selectFrom(QBoardContent.boardContent)
                .where(QBoardContent.boardContent.adminCheck.eq("0"),
                        searchByLike(boardSearchDto.getSearchType(), boardSearchDto.getSearchQuery()))
                .orderBy(QBoardContent.boardContent.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();    //2번의 select문 실행


        //조쇠 대상 리스트 결과
        List<BoardContent> content = results.getResults();




        //조회 대상 리스트의 개수(count)
        long total = results.getTotal();
        return new PageImpl<>(content,pageable,total);


    }

    @Override
    public Page<BoardContent> getnoticePage(BoardSearchDto boardSearchDto, Pageable pageable) {

        QueryResults<BoardContent> results = queryFactory
                .selectFrom(QBoardContent.boardContent)
                .where(QBoardContent.boardContent.adminCheck.eq("1"),
                        searchByLike(boardSearchDto.getSearchType(), boardSearchDto.getSearchQuery()))
                .orderBy(QBoardContent.boardContent.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();    //2번의 select문 실행


        //조쇠 대상 리스트 결과
        List<BoardContent> content = results.getResults();




        //조회 대상 리스트의 개수(count)
        long total = results.getTotal();
        return new PageImpl<>(content,pageable,total);


    }



    //게시판 아이디 찾아서 조인된 댓글 찾는 쿼리문
    @Override
    public List<Comment> getComment(Long id) {

        QueryResults<Comment> results = queryFactory
                .selectFrom(QComment.comment)
                .where(QComment.comment.boardContent.id.eq(id)
                )
                .orderBy(QComment.comment.id.asc())
                .fetchResults();    //2번의 select문 실행


        List<Comment> content = results.getResults();

        return content;

    }



    private BooleanExpression searchByLike(String searchType , String searchQuery){
        if(StringUtils.equals("name", searchType)){
            return QBoardContent.boardContent.name.like("%"+ searchQuery+"%");

        }
        else if (StringUtils.equals("boardName",searchType)){
            return QBoardContent.boardContent.boardName.like("%"+searchQuery+"%");

        }
        return null;



    }

}
