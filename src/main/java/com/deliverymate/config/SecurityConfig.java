package com.deliverymate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(config -> {
            config.loginPage("/user/login") // 내가 사용하는 로그인 페이지 GET Mapping 경로
                    .loginProcessingUrl("/user/login")
                    .usernameParameter("username") // Login html에서 id 적는 input의 name값
                    .passwordParameter("password") // Login html에서 비밀번호 적는 input의 name값
                    .defaultSuccessUrl("/main") // Login 성공 시에 이동할 GET Mapping 경로
                    .permitAll(); // 누구나 접속할수 있도록.
                })
                .logout(config -> {
                    config.logoutUrl("/user/logout") // logout 하고 싶은 postmapping 경로
                        .logoutSuccessUrl("/main") // logout 성공 시에 이동할 getmapping 경로
                            .clearAuthentication(true) // 성공시 인증풀기 (인증객체삭제)
                            .invalidateHttpSession(true) // 성공 시에 세션삭제
                            .permitAll();
                })
                .authorizeRequests(registry ->{
                    registry.requestMatchers("/user/**").permitAll()
                            .anyRequest().authenticated();
                });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
