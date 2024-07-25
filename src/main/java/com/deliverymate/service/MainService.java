package com.deliverymate.service;

import com.deliverymate.domain.ReviewDTO;
import com.deliverymate.domain.StoreDTO;
import com.deliverymate.domain.UserDTO;
import com.deliverymate.mapper.FilterMapper;
import com.deliverymate.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MainService {
    @Autowired
    private FilterMapper filterMapper;
    @Autowired
    private UserMapper userMapper;

    public List<StoreDTO> get_filter(String category, UserDTO userDTO) {
        if(Objects.isNull(userDTO)){
            return filterMapper.select_filter_by_store(category, null);
        }
        return filterMapper.select_filter_by_store(category, userDTO.getId());
    }

    public StoreDTO get_store(Integer storeNo, UserDTO userDTO) {
        if (Objects.isNull(userDTO)){
            return filterMapper.select_storeNo_by_store(storeNo, null);
        }
        System.out.println(storeNo.toString());
        return filterMapper.select_storeNo_by_store(storeNo, userDTO.getId());
    }

    public List<ReviewDTO> get_store_reviews(Integer storeNo, String order){
        return userMapper.select_store_reviews_by_storeNo(storeNo, order);
    }


    public List<StoreDTO> get_filter_search(String food, UserDTO userDTO) {
        if(Objects.isNull(userDTO)){
            return filterMapper.select_filter_by_search(food, null);
        }
        return filterMapper.select_filter_by_search(food, userDTO.getId());
    }
}
