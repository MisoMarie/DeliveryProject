package com.deliverymate.domain;

import lombok.*;

import java.util.List;

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
    private ImageDTO foodImage;
    private Integer foodStoreNo;
}
