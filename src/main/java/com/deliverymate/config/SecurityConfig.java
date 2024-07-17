package com.deliverymate.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
//                .csrf().disable()
//                .addFilterAfter(new SecurityFilter(), CsrfFilter.class)
                .authorizeHttpRequests(conf -> {
                    conf.requestMatchers("/user/mypage").authenticated()
                            .anyRequest().permitAll();
                })
                .formLogin(conf -> {
                    conf.loginPage("/user/login") // 로그인 창의 경로
                            .loginProcessingUrl("/user/login") // 로그인 창의 action에 적을 로그인 경로
                            .defaultSuccessUrl("/main") // 로그인 성공 시 이동할 페이지
                            .permitAll(); // 로그인 페이지는 누구나 올 수 있음
                })
                .logout(conf -> {
                    conf.logoutUrl("/user/logout")
                            .logoutSuccessUrl("/main")
                            .clearAuthentication(true)
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                            .permitAll();
                });
//                http.oauth2Login(conf -> {
////                    conf.loginPage("/user/login")
////                            .successHandler(new CustomOAuth2SuccessHandler())
////                            .permitAll();
//                });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
