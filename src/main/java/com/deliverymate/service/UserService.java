package com.deliverymate.service;

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

}