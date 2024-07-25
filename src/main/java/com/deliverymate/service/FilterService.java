package com.deliverymate.service;

import com.deliverymate.domain.StoreDTO;
import com.deliverymate.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService {
    @Autowired
    StoreMapper storeMapper;

    public List<StoreDTO> get_store(
            String storeName,
            String deliveryTime,
            String deliveryTip,
            String leastPrice
    ){
        return storeMapper.get_store_info(storeName,deliveryTime,deliveryTip,leastPrice);
    }
}
