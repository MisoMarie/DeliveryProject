package com.deliverymate.mapper;

import com.deliverymate.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    void insert_user(UserDTO user);
//    UserDTO select_user_by_userInfo(UserDTO loginUser);

}
