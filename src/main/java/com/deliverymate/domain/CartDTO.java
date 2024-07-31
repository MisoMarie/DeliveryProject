package com.deliverymate.domain;

import lombok.*;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Integer cartNo; // 카트 넘버
    private UserDTO user; // 음식을 주문한 유저 정보
    private StoreDTO store; // 상점정보
    private FoodDTO food; // 음식 상품 이름
    private Integer count; // 음식 수량
}
