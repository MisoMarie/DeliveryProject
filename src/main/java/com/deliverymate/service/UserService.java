package com.deliverymate.service;

import com.deliverymate.domain.ReviewDTO;
import com.deliverymate.domain.StoreDTO;
import com.deliverymate.domain.CartDTO;
import com.deliverymate.domain.UserDTO;
import com.deliverymate.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public void registerUser(UserDTO userDTO) {
        userDTO.setPassword(
                passwordEncoder.encode(userDTO.getPassword())
        );
        userMapper.insertUser(userDTO);
    }

    public boolean isUserIdExists(String id) {
        return userMapper.checkUserIdExists(id) > 0;
    }

    /*********************** 비밀번호 재설정 **************************/
    // 토큰 체크하는것
    public Boolean check_token_is_valid(String token){
        return userMapper.select_user_token_with_expiredate(token);
    }
    // 비밀번호 수정
    public void user_password_modify(String token, String password){
        String encodedPassword = passwordEncoder.encode(password);
        userMapper.update_user_password(token, encodedPassword);
    }


    /******************** 장바구니 **********************/
    // 해당 유저의 장바구니 내역 불러오기
    public List<CartDTO> get_carts(UserDTO user){
        return userMapper.selectCartsByUserId(user.getId());
    }

    /*********************** wishlist **************************/
    public List<StoreDTO> get_user_wishlist_with_stores(String id){
        return userMapper.select_wishlist_of_user(id);
    }
    public void add_user_wishlist(String userId, Integer storeNo){
        userMapper.insert_wishlist(userId, storeNo);
    }
    public void remove_user_wishlist(String userId, Integer storeNo){
        userMapper.delete_wishlist(userId, storeNo);
    }

}