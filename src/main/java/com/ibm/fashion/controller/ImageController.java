package com.ibm.fashion.controller;

import com.ibm.fashion.dto.ImageDto;
import com.ibm.fashion.entity.Image;
import com.ibm.fashion.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        var response = imageService.getAllImages();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/detect/labels")
    public ResponseEntity<List<String>> detectLabelsInImage(
            @RequestParam("image") MultipartFile image) throws IOException {
        var response = imageService.detectLabelsInImage(image.getBytes());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/detect/objects")
    public ResponseEntity<List<String>> detectObjectsInImage(
            @RequestParam("image") MultipartFile image) throws IOException {
        var response = imageService.detectObjectInImage(image.getBytes());

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(response));
    }
}
