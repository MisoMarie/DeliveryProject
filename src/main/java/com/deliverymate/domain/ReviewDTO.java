package com.deliverymate.domain;


import com.deliverymate.domain.UserDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Integer no;
    private UserDTO user;
    private Integer score;
    private String content;
    private LocalDate writeDate;
}
