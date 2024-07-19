package com.deliverymate.domain;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String password;
    private String phone;
    private String email;
    private String name;
    private ImageDTO Image;
    private String nickName;
    private List<CartDTO> carts;
}


