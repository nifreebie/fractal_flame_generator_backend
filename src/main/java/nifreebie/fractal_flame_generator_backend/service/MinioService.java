package nifreebie.fractal_flame_generator_backend.service;

import java.io.InputStream;

public interface MinioService {
    void uploadFile(String fileName, InputStream inputStream, String contentType) throws Exception;

    String getFileUrl(String fileName) throws Exception;
}
