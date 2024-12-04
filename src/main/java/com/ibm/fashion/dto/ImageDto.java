package com.ibm.fashion.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageDto {
    private Long id;
    private byte[] image;
}
