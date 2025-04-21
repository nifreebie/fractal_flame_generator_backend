package nifreebie.fractal_flame_generator_backend.controller;

import nifreebie.fractal_flame_generator_backend.domain.Image;
import nifreebie.fractal_flame_generator_backend.dto.request.GenerateImageRequest;
import nifreebie.fractal_flame_generator_backend.dto.response.GenerateImageResponse;
import nifreebie.fractal_flame_generator_backend.model.transformations.Transformation;
import nifreebie.fractal_flame_generator_backend.service.ImageService;
import nifreebie.fractal_flame_generator_backend.util.TransformationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateImage(@RequestBody GenerateImageRequest generateImageRequest) {
        List<Transformation> transformations = TransformationUtil.getTransformations(generateImageRequest.getTransformations());
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String imageUrl = imageService.generateImage(
                    generateImageRequest.getWidth(),
                    generateImageRequest.getHeight(),
                    generateImageRequest.getIterations(),
                    generateImageRequest.getSymmetry(),
                    transformations,
                    UUID.fromString(user.getUsername())
            );
            return ResponseEntity.ok(new GenerateImageResponse(imageUrl));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating image: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<String>> getUserImages() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> imageUrls = imageService.getUserImages(UUID.fromString(user.getUsername()))
                .stream()
                .map(Image::getS3Url)
                .collect(Collectors.toList());
        return ResponseEntity.ok(imageUrls);
    }

}
