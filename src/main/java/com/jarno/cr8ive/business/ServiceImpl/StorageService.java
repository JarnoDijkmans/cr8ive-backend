package com.jarno.cr8ive.business.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private final Path rootLocation = Paths.get("src/main/resources/static/uploads");

    public void store(MultipartFile file, Long postId, String fileName) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + file.getOriginalFilename());
            }

            Path postFolder = this.rootLocation.resolve("PostNumber_" + postId);

            if (!Files.exists(postFolder)) {
                Files.createDirectories(postFolder);
            }

            Files.copy(file.getInputStream(), postFolder.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
}
