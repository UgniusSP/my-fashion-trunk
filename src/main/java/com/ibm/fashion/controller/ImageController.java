package com.ibm.fashion.controller;

import com.ibm.fashion.entity.Image;
import com.ibm.fashion.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Image> saveImage(
            @RequestParam("image") MultipartFile image) {
        try {
            if(imageService.isAllowedImage(image.getBytes())){
                var imageBuild = Image.builder()
                        .image(image.getBytes())
                        .build();

                var response = imageService.saveImage(imageBuild);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
