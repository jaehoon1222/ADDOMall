package com.shop.controller;


import com.shop.dto.BoardContentDto;
import com.shop.dto.BoardSearchDto;
import com.shop.entity.BoardContent;
import com.shop.service.BoardService;
import com.shop.service.CommetService;
import com.shop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CommetService commetService;

    //공지 메인 화면
    @GetMapping(value = {"/notice" , "/notice/{page}"} )
    public String notice(BoardSearchDto boardSearchDto,
                            @PathVariable("page") Optional<Integer> page,
                            Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,30);
        Page<BoardContent> boardContents = boardService.getnoticePage(boardSearchDto ,pageable);

        model.addAttribute("boardContents" , boardContents);
        model.addAttribute("boardSearchDto" , boardSearchDto);
        model.addAttribute("maxPage" , 15);

        return "notice/noticeMain";
    }




    //공지쓰기 화면
    @GetMapping(value = "/notice/new")
    public String newNotice(Model model){

        model.addAttribute("boardContentDto",new BoardContentDto());

        return "notice/noticeForm";

    }


    //글쓰는 화면
    @PostMapping(value = "/notice/new")
    public  String writeBoard(BoardContentDto boardContentDto){



            BoardContent boardContent =BoardContent.createnoticeContent(boardContentDto);
            boardService.saveBoardContent(boardContent);

        return "redirect:/notice";
    }



    //공지 게시글 화면
    @GetMapping(value ="/notice/form/{contentId}")
    public String boardForm(@PathVariable("contentId")String contentId , Model model,
                            Optional<BoardContent> boardContent , BoardContentDto boardContentDto){

        Long idd = Long.parseLong(contentId);
        boardContent = boardService.getBoardView(idd);

        boardContentDto.setId(boardContent.get().getId());
        boardContentDto.setBoardName(boardContent.get().getBoardName());
        boardContentDto.setContent(boardContent.get().getContent());
        boardContentDto.setPassword(boardContent.get().getPassword());
        boardContentDto.setName(boardContent.get().getName());
        boardContentDto.setRegTime(boardContent.get().getRegTime());

        model.addAttribute("boardContentDto",boardContentDto);
        return "notice/noticeView";
    }





    //공지 글쓰기 폼
    @PostMapping(value = "/notice/update")
    public  String update(
            @Valid BoardContentDto boardContentDto,
            BindingResult bindingResult,Model model){

        boardService.updateNotice(boardContentDto);

        return "redirect:/notice";
    }



    //공지사항 삭제
    @GetMapping(value = "/notice/deleteBoard")
    public String deleteBoard(@RequestParam("id")String id){
        Long idd = Long.parseLong(id);
        boardService.deleteContent(idd);
        return "redirect:/notice";
    }


    //공지사항 수정
    @GetMapping(value = "/notice/update/{id}")
    public String updateBoard(Model model , @PathVariable("id")String id ,
                              Optional<BoardContent> boardContent , BoardContentDto boardContentDto){
        Long idd = Long.parseLong(id);
        boardContent = boardService.getBoardView(idd);

        boardContentDto.setId(boardContent.get().getId());
        boardContentDto.setBoardName(boardContent.get().getBoardName());
        boardContentDto.setContent(boardContent.get().getContent());
        boardContentDto.setPassword(boardContent.get().getPassword());
        boardContentDto.setName(boardContent.get().getName());
        boardContentDto.setRegTime(boardContent.get().getRegTime());

        model.addAttribute("boardContentDto",boardContentDto);
        return "notice/noticeUpdate";
    }
}






