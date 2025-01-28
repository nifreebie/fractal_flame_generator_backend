package nifreebie.fractal_flame_generator_backend.service.Impl;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import nifreebie.fractal_flame_generator_backend.service.MinioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Service
public class MinioServiceImpl implements MinioService {
    @Value("${minio.bucket-name}")
    private String bucketName;

    private final MinioClient minioClient;

    public MinioServiceImpl() {
        this.minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("nifreebie", "nis150905")
                .build();
    }

    @Override
    public void uploadFile(String fileName, InputStream inputStream, String contentType) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(inputStream, inputStream.available(), -1)
                        .contentType(contentType)
                        .build()
        );
    }

    @Override
    public String getFileUrl(String fileName) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .method(Method.GET)
                        .expiry(7, TimeUnit.DAYS)
                        .build()
        );
    }

}
