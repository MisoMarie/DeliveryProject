package com.deliverymate.domain;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private Integer storeNo;
    private String storeName;
    private String leastPrice;
    private String paymentOption;
    private String deliveryTime;
    private String deliveryTip;
    private String foodFilter;
    private List<FoodDTO> foods;

}
