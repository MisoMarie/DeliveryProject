package com.deliverymate.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    private String foodId;
    private String foodName;
    private Integer foodPrice;
    private String foodDescription;
    private byte[] foodImage;
}
