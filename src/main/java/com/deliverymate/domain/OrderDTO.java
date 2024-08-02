package com.deliverymate.domain;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer orderNo;
    private FoodDTO orderFoodId;

}
