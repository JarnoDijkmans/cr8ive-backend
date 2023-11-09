package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.persistance.config.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {


    private final FileStorageProperties fileStorageProperties;


    @Autowired
    public StorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageProperties = fileStorageProperties;
    }

    public void store(MultipartFile file, Long postId, String fileName) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + file.getOriginalFilename());
            }

            String baseDir = fileStorageProperties.getUploadDir();
            Path postFolder = Paths.get(baseDir, "PostNumber_" + postId);

            if (!Files.exists(postFolder)) {
                Files.createDirectories(postFolder);
            }

            Files.copy(file.getInputStream(), postFolder.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
}
