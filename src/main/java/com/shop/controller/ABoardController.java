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
public class ABoardController {


    private final BoardService boardService;
    private final MemberService memberService;
    private final CommetService commetService;


    
    
    

    //공지 메인 화면
    @GetMapping(value = {"/aBoard" , "/aBoard/{page}"} )
    public String ABoard(BoardSearchDto boardSearchDto,
                            @PathVariable("page") Optional<Integer> page,
                            Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,30);



        Page<BoardContent> boardContents = boardService.getABoardPage(boardSearchDto ,pageable);


        model.addAttribute("boardContents" , boardContents);
        model.addAttribute("boardSearchDto" , boardSearchDto);
        model.addAttribute("maxPage" , 15);

        return "aBoard/aBoard";
    }




    //공지쓰기 화면
    @GetMapping(value = "/aBoard/new")
    public String newABoard(Model model){

        model.addAttribute("boardContentDto",new BoardContentDto());

        return "aBoard/aBoardForm";

    }


    //글쓰는 화면
    @PostMapping(value = "/aBoard/new")
    public  String aaa(BoardContentDto boardContentDto,Model model ){


        //게시글 등록
        for (int i = 0; i < 10; i++) {
            BoardContent boardContent =BoardContent.createABoardContent(boardContentDto);
            boardService.saveBoardContent(boardContent);
        }
        return "redirect:/aBoard";
    }



    //공지 게시글 화면
    @GetMapping(value ="/aBoard/form/{contentId}")
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
        return "aBoard/aBoardView";
    }





    //공지 글쓰기 폼
    @PostMapping(value = "/aBoard/update")
    public  String update(
            @Valid BoardContentDto boardContentDto,
            BindingResult bindingResult,Model model){

        boardService.updateABoard(boardContentDto);

        return "redirect:/aBoard";
    }



    //공지사항 삭제
    @GetMapping(value = "/aBoard/deleteBoard")
    public String deleteBoard(@RequestParam("id")String id){

        Long idd = Long.parseLong(id);

        boardService.deleteContent(idd);


        return "redirect:/aBoard";
    }


    //공지사항 수정
    @GetMapping(value = "/aBoard/update/{id}")
    public String updateBoard(Model model , @PathVariable("id")String id ,
                              Optional<BoardContent> boardContent , BoardContentDto boardContentDto){
        System.out.println("안녕하냐");

        Long idd = Long.parseLong(id);
        boardContent = boardService.getBoardView(idd);

        boardContentDto.setId(boardContent.get().getId());
        boardContentDto.setBoardName(boardContent.get().getBoardName());
        boardContentDto.setContent(boardContent.get().getContent());
        boardContentDto.setPassword(boardContent.get().getPassword());
        boardContentDto.setName(boardContent.get().getName());
        boardContentDto.setRegTime(boardContent.get().getRegTime());

        model.addAttribute("boardContentDto",boardContentDto);
        return "aBoard/aUpdateBoardForm";
    }






}






