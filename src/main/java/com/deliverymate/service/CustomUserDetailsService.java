package com.deliverymate.service;


import com.deliverymate.domain.UserDTO;
import com.deliverymate.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

// login 창에서 로그인 버튼 누르면 여기로 자동으로 온다
// loadUserByUsername이 자동 실행됨
// String username <- 여기에 로그인창에서 입력한 사용자 id 값 들어옴
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 여긴 비밀번호가 없음. username으로 DB에서 해당 id의 유저를 찾음
        // usermapper 같은 mapper 사용해서 UserDTO를 찾아요
        // 유저가 없다면


        // 해당 아이디를 가지는 유저를 생성
        UserDTO loginUser = UserDTO.builder().id(username).build();
        // 해당 아이디를 가지는 유저를 DB에서 찾아옴
        UserDTO findUser = userMapper.select_user_by_userInfo(loginUser);
        if(Objects.isNull(findUser)){
            throw new UsernameNotFoundException("USER가 존재하지 않습니다");
        }



        System.out.println("로그인 성공! 유저 정보: " + findUser);

        // 유저가 있으면 유저 반환 == 로그인 성공!
        return findUser;
    }
}
