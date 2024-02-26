package com.shop.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private static final String senderEmail = "jaehoon3303@gmail.com";
    private static int number;
    // 랜덤 발송 번호를 보내기 위한 구문
    public static void createNumber(){
        //(int) Math.random() * (최댓값-최소값+1) + 최소값
        number = (int)(Math.random() * (90000)) + 10000;
    }
    // 이메일 인증을 하기 위한 createEmail 구문
    public MimeMessage createEmail(String email){
        createNumber();
        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
        return message;
    }

    public int sendEmail(String email){
        MimeMessage message = createEmail(email);
        mailSender.send(message);

        return number;
    }
    // 회원가입 완료 시 가입 이메일을 보내기 위한 send구문
    public void send(String to, String subject, String body) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(to); // 메일 수신자
            mimeMessageHelper.setSubject(subject); // 메일 제목
            mimeMessageHelper.setText(body, true); // 메일 본문 내용, HTML 여부
            mailSender.send(mimeMessage); // 메일발송
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}