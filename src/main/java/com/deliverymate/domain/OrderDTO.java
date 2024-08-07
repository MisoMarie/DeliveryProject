package com.deliverymate.domain;


import lombok.*;
import org.apache.catalina.User;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer orderNo;
    private CartDTO orderUserId;
    private String orderMerchantId;
}
