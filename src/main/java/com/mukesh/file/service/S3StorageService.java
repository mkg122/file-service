package com.mukesh.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

import static software.amazon.awssdk.core.sync.RequestBody.fromBytes;

@Service
public class S3StorageService implements StorageService{
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;


    public S3StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFile(String fileId, MultipartFile file) throws IOException {
        String key = "files/" + fileId;

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                fromBytes(file.getBytes())
        );

        return key;
    }

    @Override
    public byte[] downloadFile(String storageKey) throws IOException {
        return s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(storageKey)
                        .build()
        ).readAllBytes();
    }

    @Override
    public void deleteFile(String storageKey) throws IOException {
        s3Client.deleteObject(
                DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(storageKey)
                        .build()
        );
    }
}
