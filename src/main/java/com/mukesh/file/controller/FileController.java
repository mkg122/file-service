package com.mukesh.file.controller;

import com.mukesh.file.dto.FileDTO;
import com.mukesh.file.model.MyFile;
import com.mukesh.file.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/files/")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDTO> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            MyFile myFile = fileService.uploadFile(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(new FileDTO(myFile));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> readFile(@PathVariable String fileId) {
        try {
            byte[] fileData = fileService.readFile(fileId);
            return ResponseEntity.ok(fileData);
        } catch (NoSuchElementException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<FileDTO> updateFile(@PathVariable String fileId, @RequestParam("file") MultipartFile newFile) {
        try {
            MyFile myFile = fileService.updateFile(fileId, newFile);
            return ResponseEntity.ok(new FileDTO(myFile));
        } catch (NoSuchElementException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId) {
        try {
            fileService.deleteFile(fileId);
            return ResponseEntity.status(HttpStatus.OK).body("File deleted successfully.");
        } catch (NoSuchElementException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to delete.");
        }
    }

    @GetMapping
    public ResponseEntity<List<FileDTO>> listFiles() {
        List<MyFile> files = fileService.listFiles();
        List<FileDTO> responseFiles = files.stream().map(FileDTO::new).toList();

        return ResponseEntity.ok(responseFiles);
    }
}
