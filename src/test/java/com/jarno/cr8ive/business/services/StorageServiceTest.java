package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.configuration.FileStorageProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StorageServiceTest {
    @TempDir
    Path tempDir;

    @Test
    void testStore_post_file_successfully() {
        FileStorageProperties fileStorageProperties = mock(FileStorageProperties.class);
        StorageService storageService = new StorageService(fileStorageProperties);

        when(fileStorageProperties.getUploadDir()).thenReturn(tempDir.toString());

        MultipartFile file = new MockMultipartFile("file", "demo.jpg", "image/jpg", "Hello World".getBytes());
        storageService.store(file, 1L);

        Path storedFile = tempDir.resolve("posts").resolve("PostNumber_1").resolve("demo.jpg");

        assertTrue(Files.exists(storedFile));
    }

    @Test
    void testStore_profilePicture_successfully() {
        long userId = 123;
        FileStorageProperties fileStorageProperties = mock(FileStorageProperties.class);
        StorageService storageService = new StorageService(fileStorageProperties);

        when(fileStorageProperties.getUploadDir()).thenReturn(tempDir.toString());

        MultipartFile file = new MockMultipartFile("file", "demo.jpg", "image/jpg", "Hello World".getBytes());
        storageService.storeUserProfilePicture(userId, file);

        Path storedFile = tempDir.resolve("users").resolve("UserId_123").resolve("demo.jpg");
        assertTrue(Files.exists(storedFile));
    }


    @Test
    void testStore_emptyFile_throwsException() {
        FileStorageProperties fileStorageProperties = mock(FileStorageProperties.class);
        StorageService storageService = new StorageService(fileStorageProperties);

        when(fileStorageProperties.getUploadDir()).thenReturn(tempDir.toString());

        MultipartFile emptyFile = new MockMultipartFile("file", "", "image/jpg", "".getBytes());

        Executable executable = () -> storageService.store(emptyFile, 1L);


        RuntimeException exception = assertThrows(RuntimeException.class, executable);
        assertEquals("Failed to store empty file ", exception.getMessage());
    }

    @Test
    void testDeletePost_folderNotFound_throwsException() {
        long postId = 1L;
        FileStorageProperties fileStorageProperties = mock(FileStorageProperties.class);
        StorageService storageService = new StorageService(fileStorageProperties);

        when(fileStorageProperties.getUploadDir()).thenReturn(tempDir.toString());

        Executable executable = () -> storageService.deletePost(postId);

        RuntimeException exception = assertThrows(RuntimeException.class, executable);
        assertEquals("Folder for postId 1 does not exist", exception.getMessage());
    }

    @Test
    void testDeletePost_successfully() throws IOException {
        long postId = 1L;
        FileStorageProperties fileStorageProperties = mock(FileStorageProperties.class);
        StorageService storageService = new StorageService(fileStorageProperties);

        when(fileStorageProperties.getUploadDir()).thenReturn(tempDir.toString());

        Path postsFolder = Paths.get(tempDir.toString(), "posts");
        Path postFolder = Paths.get(postsFolder.toString(), "PostNumber_" + postId);

        Files.createDirectories(postFolder);
        Files.createFile(postFolder.resolve("file1.txt"));
        Files.createFile(postFolder.resolve("file2.txt"));

        storageService.deletePost(postId);

        assertFalse(Files.exists(postFolder));
    }
}