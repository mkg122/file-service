package com.mukesh.file.service;

import com.mukesh.file.model.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    MyFile uploadFile(MultipartFile file) throws IOException;

    byte[] readFile(String fileId) throws IOException;

    MyFile updateFile(String fileId, MultipartFile newFile) throws IOException;

    void deleteFile(String fileId) throws IOException;

    List<MyFile> listFiles();
}
