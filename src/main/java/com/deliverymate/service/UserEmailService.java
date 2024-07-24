package com.deliverymate.service;


import com.deliverymate.domain.UserDTO;
import com.deliverymate.mapper.UserMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
public class UserEmailService {
    private final String SEND_EMAIL_FROM = "wsw8186@naver.com";
    private final int PASSWORD_RESET_TOKEN_EXPIRE_MINUTE = 15; // 패스워드 재설정 만료 기간


    @Autowired private UserMapper userMapper;
    @Autowired private JavaMailSender mailSender;


    public Boolean find_user_id(String email){
        UserDTO user = UserDTO.builder().email(email).build();
        // 해당 이메일을 가지는 유저를 찾는다
        UserDTO findedUser = userMapper.select_user_by_userInfo(user);
        if(Objects.isNull(findedUser)){
            return true;
        }
        // 해당 이메일로 데이터를 전송한다
        String subject = "[DELIVERY MATE] 아이디 찾기 결과입니다";
        String text = "<b>아이디: </b>" + findedUser.getId();
        try {
            send_mail(email, subject, text);
            return true;
        }catch (MessagingException e){
            log.error("send_mail Error: " + e.getMessage());
        }
        return false;
    }

    public Boolean reset_user_password(String userId, String email){
        UserDTO user = UserDTO.builder().id(userId).email(email).build();
        // 해당 아이디와 이메일을 가지는 유저를 찾는다
        UserDTO findedUser = userMapper.select_user_by_userInfo(user);
        if(Objects.isNull(findedUser)){
            return true;
        }

        // 토큰으로 사용할 값을 하나 랜덤으로 생성한다. (8자로)
        String token = UUID.randomUUID().toString()
                .replace("-", "").substring(0, 8);
        LocalDateTime tokenExpireDate = LocalDateTime.now().plusMinutes(PASSWORD_RESET_TOKEN_EXPIRE_MINUTE);
        // 생성된 토큰을 해당 유저에 설정한다
        userMapper.update_user_pw_token(userId, token, tokenExpireDate);
        // 생성된 토큰과 함께 이메일을 전송한다
        try{
            String subject = "[DELIVERY MATE] 비밀번호 재설정";
            String text = "<a href='http://localhost:8080/user/reset/password?token=" + token + "'>재설정 링크로 이동</a>";
            send_mail(email, subject, text);
            return true;
        }
        catch (MessagingException e){
            log.error("send_mail Error: " + e.getMessage());
        }
        return false;
    }


    public void send_mail(String to, String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(SEND_EMAIL_FROM); // 보내는 사람 설정
        helper.setTo(to); // 받는 사람 설정
        helper.setSubject(subject); // 제목 설정
        helper.setText(text, true); // 내용 설정
        mailSender.send(mimeMessage); // 발송
    }




}
