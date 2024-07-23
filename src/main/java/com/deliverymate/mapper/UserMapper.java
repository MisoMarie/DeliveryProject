package com.deliverymate.mapper;

import com.deliverymate.domain.CartDTO;
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

    List<CartDTO> selectCartsByUserId(String userId);



}
