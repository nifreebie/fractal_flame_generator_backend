package nifreebie.fractal_flame_generator_backend.service.Impl;

import nifreebie.fractal_flame_generator_backend.domain.Image;
import nifreebie.fractal_flame_generator_backend.domain.User;
import nifreebie.fractal_flame_generator_backend.model.FractalImage;
import nifreebie.fractal_flame_generator_backend.model.Rect;
import nifreebie.fractal_flame_generator_backend.model.transformations.Transformation;
import nifreebie.fractal_flame_generator_backend.repository.ImageRepository;
import nifreebie.fractal_flame_generator_backend.repository.UserRepository;
import nifreebie.fractal_flame_generator_backend.service.ImageService;
import nifreebie.fractal_flame_generator_backend.service.generator.MultiThreadGenerator;
import nifreebie.fractal_flame_generator_backend.util.ImageUtil;
import nifreebie.fractal_flame_generator_backend.util.SerializableBufferedImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private MinioServiceImpl minioService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public String generateImage(int width,
                                int height,
                                int iterations,
                                int symmetry,
                                List<Transformation> transformations,
                                UUID user_id) throws Exception {
        String fileName = "image_" + UUID.randomUUID() + ".png";
        MultiThreadGenerator generator = new MultiThreadGenerator(
                5,
                5,
                iterations,
                symmetry,
                transformations);
        Rect world = new Rect(-4, -3, 8, 6);
        FractalImage fractalImage = generator.render(width, height, world);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        SerializableBufferedImage bufferedImage = ImageUtil.convertFractalImageToBufferedImage(fractalImage);
        ImageIO.write(bufferedImage, "png", byteStream);
        byte[] imageBytes = byteStream.toByteArray();
        minioService.uploadFile(fileName, new ByteArrayInputStream(imageBytes), "image/png");
        String fileUrl = minioService.getFileUrl(fileName);
        User user = userRepository.getById(user_id);
        Image image = Image.builder()
                .name(fileName)
                .s3Url(fileUrl)
                .user(user)
                .build();
        imageRepository.save(image);
        return fileUrl;
    }

    @Override
    public List<Image> getUserImages(UUID userId) {
        return imageRepository.findByUserId(userId);
    }
}
