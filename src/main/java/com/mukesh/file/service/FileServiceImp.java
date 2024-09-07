package com.mukesh.file.service;

import com.mukesh.file.dao.FileRepository;
import com.mukesh.file.model.MyFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {
    private final FileRepository fileRepository;
    private final StorageService storageService;

    public FileServiceImp(FileRepository fileRepository, StorageService storageService) {
        this.fileRepository = fileRepository;
        this.storageService = storageService;
    }

    @Override
    public MyFile uploadFile(MultipartFile file) throws IOException {
        String fileId = UUID.randomUUID().toString();
        String storageKey = storageService.uploadFile(fileId, file);

        MyFile myfile = new MyFile();
        myfile.setId(fileId);
        myfile.setFilename(file.getOriginalFilename());
        myfile.setSize(file.getSize());
        myfile.setType(file.getContentType());
        myfile.setCreatedAt(LocalDateTime.now());
        myfile.setUpdatedAt(LocalDateTime.now());

        return fileRepository.save(myfile);
    }

    @Override
    public byte[] readFile(String fileId) throws IOException {
        MyFile myfile = fileRepository.findById(fileId)
                .orElseThrow(() -> new NoSuchElementException("File not found"));

        return storageService.downloadFile("files/" + fileId);
    }

    @Override
    public MyFile updateFile(String fileId, MultipartFile newFile) throws IOException {
        MyFile myfile = fileRepository.findById(fileId)
                .orElseThrow(() -> new NoSuchElementException("File not found"));

        String storageKey = storageService.uploadFile(fileId, newFile);

        myfile.setFilename(newFile.getOriginalFilename());
        myfile.setSize(newFile.getSize());
        myfile.setType(newFile.getContentType());
        myfile.setUpdatedAt(LocalDateTime.now());
        return fileRepository.save(myfile);
    }

    @Override
    public void deleteFile(String fileId) throws IOException {
        MyFile myfile = fileRepository.findById(fileId)
                .orElseThrow(() -> new NoSuchElementException("File not found"));

        storageService.deleteFile("files/" + fileId);

        fileRepository.deleteById(fileId);
    }

    @Override
    public List<MyFile> listFiles() {
        return fileRepository.findAll();
    }

}
