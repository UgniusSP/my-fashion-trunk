package com.ibm.fashion;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.LocalizedObjectAnnotation;
import com.ibm.fashion.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImageServiceTest {

    @Mock
    private CloudVisionTemplate cloudVisionTemplate;

    @InjectMocks
    private ImageService imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDetectLabelsInImage() {
        byte[] imageBytes = new byte[]{1, 2, 3};
        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        AnnotateImageResponse mockResponse = mock(AnnotateImageResponse.class);
        EntityAnnotation annotation1 = EntityAnnotation.newBuilder().setDescription("Label1").build();
        EntityAnnotation annotation2 = EntityAnnotation.newBuilder().setDescription("Label2").build();

        when(cloudVisionTemplate.analyzeImage(resource, Feature.Type.LABEL_DETECTION)).thenReturn(mockResponse);
        when(mockResponse.getLabelAnnotationsList()).thenReturn(List.of(annotation1, annotation2));

        List<String> labels = imageService.detectLabelsInImage(imageBytes);

        assertNotNull(labels);
        assertEquals(2, labels.size());
        assertTrue(labels.contains("Label1"));
        assertTrue(labels.contains("Label2"));
    }


    @Test
    void testDetectObjectInImage() {
        AnnotateImageResponse mockResponse = mock(AnnotateImageResponse.class);
        LocalizedObjectAnnotation mockObjectAnnotation = mock(LocalizedObjectAnnotation.class);

        when(mockObjectAnnotation.getName()).thenReturn("Detected Object");
        when(mockResponse.getLocalizedObjectAnnotationsCount()).thenReturn(1);
        when(mockResponse.getLocalizedObjectAnnotations(0)).thenReturn(mockObjectAnnotation);

        when(cloudVisionTemplate.analyzeImage(any(ByteArrayResource.class), eq(Feature.Type.OBJECT_LOCALIZATION)))
                .thenReturn(mockResponse);

        byte[] image = new byte[]{1, 2, 3};
        String detectedObject = imageService.detectObjectInImage(image);

        assertNotNull(detectedObject);
        assertEquals("Detected Object", detectedObject);
    }
}