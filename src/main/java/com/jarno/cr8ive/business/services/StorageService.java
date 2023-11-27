package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.configuration.FileStorageProperties;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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
            Path postsFolder = Paths.get(baseDir, "posts");
            Path postFolder = postsFolder.resolve("PostNumber_" + postId);

            if (!Files.exists(postFolder)) {
                Files.createDirectories(postFolder);
            }

            Files.copy(file.getInputStream(), postFolder.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    public void storeUserProfilePicture(long id, MultipartFile file){
        try {
            if (file == null) {
                String baseDir = fileStorageProperties.getUploadDir();
                File defaultImage = new File(baseDir, "default-image-url.png");
                FileInputStream input = new FileInputStream(defaultImage);
                file = new MockMultipartFile("file",
                        defaultImage.getName(), "image/*", IOUtils.toByteArray(input));
            }

            String baseDir = fileStorageProperties.getUploadDir();
            Path postsFolder = Paths.get(baseDir, "users");
            Path userFolder = postsFolder.resolve("UserId_" + id);

            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
            }
            Files.copy(file.getInputStream(), userFolder.resolve(Objects.requireNonNull(file.getOriginalFilename())));

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
}
