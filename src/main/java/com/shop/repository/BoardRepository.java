package com.shop.repository;

import com.shop.entity.BoardContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardContent, Long>  , QuerydslPredicateExecutor<BoardContent>, BoardRepositoryCustom{


    Optional<BoardContent> findById(Long id);



}
