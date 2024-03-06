package com.shop.controller;


import com.shop.dto.BoardContentDto;
import com.shop.dto.BoardSearchDto;
import com.shop.dto.PwdCheck;
import com.shop.entity.BoardContent;
import com.shop.entity.Comment;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {


    private final BoardService boardService;
    private final MemberService memberService;
    private final CommetService commetService;




    //자유게시판 글쓰기 화면
    @GetMapping(value = "/freeBoard/new")
    public String newFreeBoard(Model model){

        model.addAttribute("boardContentDto",new BoardContentDto());

        return "freeBoard/freeBoardForm";

    }


    //글쓰는 화면
    @PostMapping(value = "/freeBoard/new")
    public  String aaa(@Valid BoardContentDto boardContentDto,
                       BindingResult bindingResult,Model model ){

        //유효성 검사
        if(bindingResult.hasErrors()){
            return "freeBoard/freeBoardForm";
        }

        //게시글 등록
        for (int i = 0; i < 10; i++) {
            BoardContent boardContent =BoardContent.createBoardContent(boardContentDto);
            boardService.saveBoardContent(boardContent);
        }
        return "redirect:/freeBoard";
    }
    
    
    

    //자유게시판 메인 화면
    @GetMapping(value = {"/freeBoard" , "/freeBoard/{page}"} )
    public String freeBoard(BoardSearchDto boardSearchDto,
                            @PathVariable("page") Optional<Integer> page,
                            Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,30);



        Page<BoardContent> boardContents = boardService.getBoardPage(boardSearchDto ,pageable);

        model.addAttribute("boardContents" , boardContents);
        model.addAttribute("boardSearchDto" , boardSearchDto);
        model.addAttribute("maxPage" , 15);

        return "freeBoard/freeBoard";
    }


    //자유게시판 게시글 화면
    @GetMapping(value ="/freeBoard/form/{contentId}")
    public String boardForm(@PathVariable("contentId")String contentId , Model model,
                            Optional<BoardContent> boardContent , BoardContentDto boardContentDto){

        Long idd = Long.parseLong(contentId);
        boardContent = boardService.getBoardView(idd);
        
        //댓글 리스트
        List<Comment> comments = boardService.commentsList(Long.valueOf(contentId));
        
        //대댓글 리스트
        List<Comment> a = boardService.commentsList(Long.valueOf(contentId));

        //댓글은 지우고 대댓글만 남기는 작업
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).getReCommentID()==null){
                a.remove(i);
            }
        }



        boardContentDto.setId(boardContent.get().getId());
        boardContentDto.setBoardName(boardContent.get().getBoardName());
        boardContentDto.setContent(boardContent.get().getContent());
        boardContentDto.setPassword(boardContent.get().getPassword());
        boardContentDto.setName(boardContent.get().getName());
        boardContentDto.setRegTime(boardContent.get().getRegTime());

        model.addAttribute("a",a);
        model.addAttribute("comments" ,comments);
        model.addAttribute("boardContentDto",boardContentDto);
        return "freeBoard/boardView";
    }







    //자유게시판 수정 및 삭제시 비밀번호 체크 화면
    @GetMapping(value = "/freeBoard/passwordView")
    public String passwordCheck(@RequestParam("id")String id,
                                @RequestParam("option")String option,
                                Optional<BoardContent> boardContent,
                                Model model){

        Long idd = Long.parseLong(id);
        boardContent = boardService.getBoardView(idd);

        model.addAttribute("id",boardContent.get().getId());
        model.addAttribute("checkPw",boardContent.get().getPassword());
        
        //삭제냐 수정이냐 체크 0수정 1삭제
        model.addAttribute("option",option);
        return "freeBoard/passwordView";

    }





    //비밀번호 확인 ajax
    @PostMapping(value = "/freeBoard/passwordCheck")
    public @ResponseBody  String passwordCheck(PwdCheck pwdCheck){


        //옵션 0번 수정 프린트는 확인용
        if(pwdCheck.getInputPWD().equals(pwdCheck.getPWD()) && pwdCheck.getOption().equals("0")  ){

            //리턴값으로  ajax 분기
            return "수정성공";
        }




        //옵션 1번 삭제 프린트는 확인용
        if(pwdCheck.getInputPWD().equals(pwdCheck.getPWD()) && pwdCheck.getOption().equals("1")  ){
            System.out.println(pwdCheck.getPWD());
            System.out.println(pwdCheck.getInputPWD());
            System.out.println(pwdCheck.getId());

            //리턴값으로  ajax 분기
            return "삭제성공";
        }

        return "비밀번호가 맞지않습니다.";

    }

    //비밀번호 맞으면 실행되는 삭제
    @GetMapping(value = "/freeBoard/deleteBoard")
    public String deleteBoard(@RequestParam("id")String id){

        Long idd = Long.parseLong(id);



        List<Comment> comments = boardService.commentsList(Long.valueOf(id));

        for (int i = 0; i < comments.size(); i++) {
            long a = comments.get(i).getId();
            commetService.deleteCommet(a);

        }



        boardService.deleteContent(idd);


        return "redirect:/freeBoard";
    }



    //th:href="'/freeBoard/form/'+${boardContent.id}"
    //비밀번호 맞으면 수정 화면
    //자유게시판 글쓰기 화면
    @GetMapping(value = "/freeBoard/update/{id}")
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
        return "freeBoard/updateBoardForm";
    }



    //자유게시판 글쓰기 폼
    @PostMapping(value = "/freeBoard/update")
    public  String update(
                          @Valid BoardContentDto boardContentDto,
                       BindingResult bindingResult,Model model){

        boardService.updateBoard(boardContentDto);
//
//        if(bindingResult.hasErrors()){
//            return "/freeBoard/update/{id}";
//        }


        return "redirect:/freeBoard";
    }





    
    
    
    
    
    
    
    
    
    
}






