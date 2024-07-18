package com.deliverymate.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = {"image", "imageURL", "file"})
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private Integer no;
    private MultipartFile file;
    private byte[] image;
    private String imageURL;

    public void setFile(MultipartFile file) {
        this.file = file;
        try {
            if(Objects.nonNull(file)) {
                this.image = file.getBytes();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setImage(byte[] image) {
        this.image = image;
        String url = Base64.getEncoder().encodeToString(this.image);
        this.imageURL = ("data:image/*;base64, " + url);
    }
}
