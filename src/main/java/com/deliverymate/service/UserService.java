package com.deliverymate.service;

import com.deliverymate.domain.StoreDTO;
import com.deliverymate.domain.UserDTO;
import com.deliverymate.mapper.UserMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired private UserMapper userMapper;
    @Autowired private PasswordEncoder passwordEncoder;

    public boolean user_register(UserDTO userDTO) {

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userMapper.insert_user(userDTO);
        return true;
    }

//    public StoreDTO get_store(StoreDTO storeDTO) {
//
//
//    }

    public UserDTO get_user_info(UserDTO userDTO) {

        return userMapper.get_user_info(userDTO);
    }


}
