package com.mukesh.file.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class MyFile {
    @Id
    String id;
    String filename;
    long size;
    String type;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;


}
