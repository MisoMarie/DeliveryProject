package com.deliverymate.config;

import com.deliverymate.service.CustomOauth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    oauth2 자체에 service 달려있어서 가져올 필요없음
//    private final CustomOauth2UserService customOauth2UserService;
//
//    public SecurityConfig(CustomOauth2UserService customOauth2UserService) {
//        this.customOauth2UserService = customOauth2UserService;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/user/**", "/css/**", "/js/**", "/img/**", "/filter", "/store").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/user/login") // 내가 사용하는 로그인 페이지 GET Mapping 경로
                                .loginProcessingUrl("/user/login") //해당 URL로 POST 요청을 보내면 Spring Security가 이를 처리하여 사용자 인증을 수행
                                .usernameParameter("username") // Login html에서 id 적는 input의 name값
                                .passwordParameter("password") // Login html에서 비밀번호 적는 input의 name값
                                .defaultSuccessUrl("/main", true) // Login 성공 시에 이동할 GET Mapping 경로
                                .permitAll() // 누구나 접속할수 있도록.
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/user/logout") // logout 하고 싶은 postmapping 경로
                                .logoutSuccessUrl("/main") // logout 성공 시에 이동할 getmapping 경로
                                .clearAuthentication(true) // 성공시 인증풀기 (인증객체삭제)
                                .invalidateHttpSession(true) // 성공 시에 세션삭제
                                .permitAll()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/user/login")
                                .defaultSuccessUrl("/main", true)  // OAuth2 로그인 성공 시 메인 페이지로 이동
                                .userInfoEndpoint()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
