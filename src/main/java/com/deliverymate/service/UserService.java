package com.deliverymate.service;

import com.deliverymate.domain.UserDTO;
import com.deliverymate.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public void registerUser(UserDTO user) {
        userMapper.insertUser(user);
    }

    public boolean isUserIdExists(String id) {
        return userMapper.checkUserIdExists(id) > 0;
    }
}