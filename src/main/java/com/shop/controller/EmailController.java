package com.shop.controller;


import com.shop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailService EmailService;

    @ResponseBody
    @PostMapping("/email")
    public String EmailSend(@RequestBody Map<String, Object> param,Model model){

        String email = (String) param.get("email");


        int number = EmailService.sendEmail(email);
        System.out.println("number!!!!!!!!!!!!!"+number);
        String num = "" + number;
        return num;
    }

}