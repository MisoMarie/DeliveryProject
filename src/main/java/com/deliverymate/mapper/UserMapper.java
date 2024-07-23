package com.deliverymate.mapper;

import com.deliverymate.domain.StoreDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.deliverymate.domain.UserDTO;  // Ensure you import UserDTO
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
<<<<<<< Updated upstream
=======
import java.util.List;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======

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
>>>>>>> Stashed changes
}
