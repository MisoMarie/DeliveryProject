package com.deliverymate.service;

import com.deliverymate.domain.StoreDTO;
import com.deliverymate.mapper.FilterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    @Autowired
    private FilterMapper filterMapper;

    public List<StoreDTO> get_filter(String category) {
        return filterMapper.select_filter_by_store(category);
    }

    public StoreDTO get_store(Integer storeNo) {
        StoreDTO store = filterMapper.select_storeNo_by_store(storeNo);
        System.out.println(store);
        return store;
    }
}
