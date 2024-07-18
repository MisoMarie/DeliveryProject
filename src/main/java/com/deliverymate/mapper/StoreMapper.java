package com.deliverymate.mapper;


import com.deliverymate.domain.StoreDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {

    List<StoreDTO> get_store_info(
            @Param("storeName") String storeName,
            @Param("deliveryTime") String deliveryTime,
            @Param("deliveryTip") String deliveryTip,
            @Param("leastPrice") String leastPrice
    );

}
