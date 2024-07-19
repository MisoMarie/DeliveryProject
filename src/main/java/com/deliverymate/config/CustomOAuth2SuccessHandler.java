package com.deliverymate.config;

import com.deliverymate.domain.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

//public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        // OAuth2 인증이 된 객체 (접근 주체인 principle)를 가져온다
//        Object principal = authentication.getPrincipal();
//        // 인증된 유저가 UserDTO 객체였다. => 로그인이 완료된 상태. 추가로 회원가입 필요없음.
//        if (principal instanceof UserDTO){
//            response.sendRedirect("/main");
//            return;
//        }
//        // 인증된 유저가 단순 소셜 로그인만 했고, 기존에 회원가입되어 있지 않은 유저임.
//        authentication.setAuthenticated(false); // 인증을 취소함.
//        request.getSession().invalidate(); // 세션을 삭제하므로서 로그아웃 시킴.
//        response.sendRedirect("/user/register?notRegister"); // 회원가입 창으로 이동시킴
//
//    }
//}
