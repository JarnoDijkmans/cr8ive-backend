package com.jarno.cr8ive.controller;


import com.jarno.cr8ive.configuration.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:5173")
public class FileController {

    private final FileStorageProperties fileStorageProperties;

    @Autowired
    public FileController(FileStorageProperties fileStorageProperties){
        this.fileStorageProperties = fileStorageProperties;
    }

    @GetMapping("/{postId}/{fileName}")
    public ResponseEntity<Resource> serveFilePost(@PathVariable long postId, @PathVariable String fileName) {
        String baseDir = fileStorageProperties.getUploadDir();

        Path filePath = Paths.get(baseDir, "posts/","PostNumber_" + postId, fileName);
        Resource resource = new FileSystemResource(filePath);

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}/{fileName}")
    public ResponseEntity<Resource> serveFileUser(@PathVariable long userId, @PathVariable String fileName) {
        String baseDir = fileStorageProperties.getUploadDir();

        Path filePath = Paths.get(baseDir, "users/","UserId_" + userId, fileName);
        Resource resource = new FileSystemResource(filePath);

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
