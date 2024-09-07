package com.mukesh.file.dto;

import com.mukesh.file.model.MyFile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileDTO {
    private String fileId;
    private String fileName;
    private long size;
    private String fileType;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public FileDTO(MyFile myFile){
        this.fileId = myFile.getId();
        this.fileName = myFile.getFilename();
        this.size = myFile.getSize();
        this.fileType = myFile.getType();
        this.createdAt = myFile.getCreatedAt();
        this.updateAt = myFile.getUpdatedAt();
    }
}

