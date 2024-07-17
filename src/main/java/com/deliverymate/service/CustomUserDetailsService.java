package com.deliverymate.service;

import com.deliverymate.domain.UserDTO;
import com.deliverymate.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

//@Service
//@Slf4j
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired private UserMapper userMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("로그인 시도하는 유저명..: " + username);
//        // 해당 아이디를 가지는 유저를 생성
//        UserDTO loginUser = UserDTO.builder().id(username).build();
//        // 해당 아이디를 가지는 유저를 DB에서 찾아옴
//        UserDTO findUser = userMapper.select_user_by_userInfo(loginUser);
//        if(Objects.isNull(findUser)){
//            throw new UsernameNotFoundException("USER가 존재하지 않습니다");
//        }
//        return findUser;
//    }
//}
