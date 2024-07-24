package com.deliverymate.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Integer cartNo; // 카트 넘버
    private UserDTO userId; // 회원
    private FoodDTO foodId; // 음식 상품
    private Integer count; // 음식 수량

}
