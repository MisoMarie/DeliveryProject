package com.deliverymate.mapper;

import com.deliverymate.domain.StoreDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface FilterMapper {

    List<StoreDTO> select_filter_by_store(@Param("category") String category);


    StoreDTO select_storeNo_by_store(@Param("storeNo") Integer storeNo);

}