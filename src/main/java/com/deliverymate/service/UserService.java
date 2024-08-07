package com.deliverymate.service;

import com.deliverymate.domain.*;
import com.deliverymate.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
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

@Log4j2
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
    public void add_cart(CartDTO cart){
        userMapper.insertCart(cart);
    }



    // 장바구니에 상품 제거하기
    public void delete_cart(List<CartDTO> carts){
        // 장바구니에 현재 회원 설정
        userMapper.deleteCart(carts);
    }

    /****************** 결제 *****************************/
    public void add_order(OrderDTO order, CartDTO cart){
        order.setOrderUserId(cart); // 결제자 (주문자) 설정
        order.setOrderFoodId(cart);
        System.out.println(cart);
        userMapper.insertOrder(order); // 주문정보
        userMapper.insertOrderProducts(order); // 주문 상품들
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