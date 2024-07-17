package com.deliverymate.domain;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String password;
    private String phone;
    private String email;
    private String name;
    private byte[] userImage;
    private String nickName;
    private List<CartDTO> carts;
}
