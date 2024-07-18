package com.deliverymate.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.deliverymate.domain.UserDTO;  // Ensure you import UserDTO

@Mapper
public interface UserMapper {

    void insertUser(UserDTO user);


    //int
    int checkUserIdExists(String id);
}
