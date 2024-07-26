package com.deliverymate.mapper;

import com.deliverymate.domain.CartDTO;
import com.deliverymate.domain.ReviewDTO;
import com.deliverymate.domain.StoreDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.deliverymate.domain.UserDTO;  // Ensure you import UserDTO
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {

    void insertUser(UserDTO user);


    //int
    int checkUserIdExists(String id);

    UserDTO select_user_by_userInfo(UserDTO userDTO);


    //패스워드 관련 부문
    Boolean select_user_token_with_expiredate(
            @Param("token") String token
    );

    void update_user_pw_token(
            @Param("id") String id,
            @Param("token") String token,
            @Param("expireDate") LocalDateTime expireDate
    );

    void update_user_password(
            @Param("token") String token,
            @Param("password") String password
    );

    /****************** wishlist ********************/
    List<StoreDTO> select_wishlist_of_user(String id);
    void insert_wishlist(
            @Param("userId") String userId,
            @Param("storeNo") Integer storeNo
    );
    void delete_wishlist(
            @Param("userId") String userId,
            @Param("storeNo") Integer storeNo
    );

    /********************* 장바구니 **************************/
    List<CartDTO> selectCartsByUserId(String userId);
    CartDTO selectCartDuplicated(CartDTO cart); // 장바구니에 해당 상품이 존재?
    void insertCart(CartDTO cart); // 장바구니에 상품 추가
    void updateCartAmount(
            @Param("cartNo") Integer cartNo,
            @Param("amount") Integer amount
    ); // 장바구니에 존재하는 상품의 수량 변경
    void deleteCart(List<CartDTO> carts); // 해당 장바구니들을 삭제




    /****************** review ********************/
    // 해당 방의 모든 REIVEW 가져오기
    List<ReviewDTO> select_store_reviews_by_storeNo(
            @Param("storeNo") Integer storeNo,
            @Param("order") String order
    );


}
