package com.deliverymate.service;

import com.deliverymate.domain.UserDTO;
import com.deliverymate.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserMapper userMapper;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String clientName = userRequest.getClientRegistration().getClientName();
        // 기본 OAuth2UserService의 로직을 호출하여 사용자 정보를 가져옵니다.
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 사용자 정보 로깅 (디버깅용)
        System.out.println("=========== CustomOauth2UserService loadUser ==========");
        System.out.println(userRequest);
        System.out.println("로그인 중인 clientName: " + userRequest.getClientRegistration().getClientName());
        System.out.println("============== 가져온 유저의 정보... ============== ");
        oAuth2User.getAttributes().forEach((key, value) -> {
            System.out.println("key:" + key);
            System.out.println("value: " + value);
        });
        System.out.println("발급받은 TOKEN 객체:" + userRequest.getAccessToken());
        Map<String, Object> attributeMap = oAuth2User.getAttributes();
        String id = null;
        switch (clientName){
            case "kakao" :
                id = attributeMap.get("id").toString();
                break;
            case "naver" :
                Map<String, Object> naverObject = (Map)attributeMap.get("response");
                id = naverObject.get("id").toString();
                break;
            case "Google" :
                id = attributeMap.get("sub").toString();
        }

        UserDTO user = userMapper.select_user_by_userInfo(UserDTO.builder().id(id).build());
         if(user == null){
             user = UserDTO.builder()
                     .id(id)
                     .password(UUID.randomUUID().toString().substring(0, 8))
                     .phone(UUID.randomUUID().toString().substring(0, 8))
                     .email(UUID.randomUUID().toString().substring(0, 8))
                     .name("sns_user")
                     .nickname("sns_user")
                     .build();
             userMapper.insertUser(user);
         }


        // 추가적인 사용자 처리 로직을 여기에 작성합니다.
        return user;
    }
}
