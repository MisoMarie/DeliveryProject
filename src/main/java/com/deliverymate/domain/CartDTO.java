package com.deliverymate.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Integer cartNo;
    private FoodDTO foodDTO;
    private Integer count;

}
