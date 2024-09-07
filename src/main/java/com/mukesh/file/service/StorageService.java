package com.mukesh.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String uploadFile(String fileId, MultipartFile file) throws IOException;

    byte[] downloadFile(String storageKey) throws IOException;

    void deleteFile(String storageKey) throws IOException;
}
