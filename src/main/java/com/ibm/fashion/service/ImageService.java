package com.ibm.fashion.service;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.ibm.fashion.entity.Image;
import com.ibm.fashion.repository.CategoryRepository;
import com.ibm.fashion.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;

    private final CloudVisionTemplate cloudVisionTemplate;

    public Image saveImage(byte[] imageBytes) {
        if (isAllowedImage(imageBytes)) {
            var image = Image.builder().image(imageBytes).build();
            return imageRepository.save(image);
        }
        return null;
    }

    public List<String> detectLabelsInImage(byte[] image) {
        ByteArrayResource resource = new ByteArrayResource(image);

        AnnotateImageResponse response =
                this.cloudVisionTemplate
                        .analyzeImage(resource, Feature.Type.LABEL_DETECTION);

        System.out.println(response.getLabelAnnotationsList().stream()
                .map(EntityAnnotation::getDescription)
                .toList());

        return response.getLabelAnnotationsList().stream()
                .map(EntityAnnotation::getDescription)
                .toList();
    }

    public String detectObjectInImage(byte[] image) {
        ByteArrayResource resource = new ByteArrayResource(image);

        AnnotateImageResponse response =
                this.cloudVisionTemplate
                        .analyzeImage(resource, Feature.Type.OBJECT_LOCALIZATION);

        if(response.getLocalizedObjectAnnotationsCount() == 0){
            return null;
        }

        System.out.println(response.getLocalizedObjectAnnotations(0).getName());

        return response.getLocalizedObjectAnnotations(0).getName();
    }

    private boolean isAllowedImage(byte[] image){
        List<String> detectedLabels = detectLabelsInImage(image);
        String detectedObject = detectObjectInImage(image);

        return (detectedLabels.stream().anyMatch(this::isAllowedItem) || isAllowedItem(detectedObject))
                && (detectedLabels.stream().noneMatch(this::isProhibitedItem) && !isProhibitedItem(detectedObject));
    }

    private boolean isAllowedItem(String item){
        List<String> allowedItems = categoryRepository.findAllByIsAllowed(true);
        return allowedItems.contains(item);
    }

    private boolean isProhibitedItem(String item){
        List<String> prohibitedItems = categoryRepository.findAllByIsAllowed(false);
        return prohibitedItems.contains(item);
    }

}
