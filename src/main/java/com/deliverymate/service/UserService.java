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
import java.util.Objects;

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

    // 장바구니에 상품 추가하기
    public void add_cart(UserDTO user, CartDTO cart){
        // 장바구니에 현재 회원 설정
        cart.setUserId(user);
        // 이미 해당 정보와 같은 상품이 장바구니에 존재하는가?
        CartDTO existCart = userMapper.selectCartDuplicated(cart);
        // 존재하지 않았다면
        if(Objects.isNull(existCart)){
            // 새로 장바구니에 추가한다
            userMapper.insertCart(cart);
        }
        // 장바구니에 이미 존재했다!
        else{
            // 기존 장바구니의 내용으로 업데이트를 시켜야 함!
            // 기존 장바구니 번호와, 사용자가 전달한 수량을 전달한다
            userMapper.updateCartAmount(existCart.getCartNo(), cart.getCount());
        }
    }

    // 장바구니에 상품 제거하기
    public void delete_cart(List<CartDTO> carts){
        // 장바구니에 현재 회원 설정
        userMapper.deleteCart(carts);
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