package com.deliverymate.domain;

import java.util.List;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String password;
    private String phone;
    private String email;
    private String name;
    private ImageDTO image;
    private String nickname;
    private List<CartDTO> carts;
}