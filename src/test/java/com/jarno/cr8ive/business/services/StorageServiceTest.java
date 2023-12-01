//package com.jarno.cr8ive.business.services;
//
//import com.jarno.cr8ive.configuration.FileStorageProperties;
//import com.jarno.cr8ive.domain.user.IUser;
//import org.junit.jupiter.api.Test;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class StorageServiceTest {
//    @Test
//     void testStore() throws IOException {
//        // Arrange
//        FileStorageProperties properties = mock(FileStorageProperties.class);
//        when(properties.getUploadDir()).thenReturn("/uploads");
//
//        long postId = 1L;
//
//        String name = "test.txt";
//        String originalFileName = "test.txt";
//        String contentType = "text/plain";
//        byte[] content = "Hello World".getBytes();
//
//        MockMultipartFile multipartFile = new MockMultipartFile(name, originalFileName, contentType, content);
//        String postFolderName = "PostNumber_" + postId;
//        Path postFolderPath = Paths.get(properties.getUploadDir(), "posts", postFolderName);
//
//        StorageService storageService = new StorageService(properties);
//
//        // Act
//        storageService.store(multipartFile, postId);
//
//        // Assert
//        assertTrue(Files.exists(postFolderPath.resolve(multipartFile.getOriginalFilename())));
//        assertEquals("Hello World", new String(Files.readAllBytes(postFolderPath.resolve(multipartFile.getOriginalFilename()))));
//    }
//
//    @Test
//    void testStoreUserProfilePicture() throws IOException {
//        // Arrange
//        FileStorageProperties properties = mock(FileStorageProperties.class);
//        when(properties.getUploadDir()).thenReturn("/temp");
//
//        IUser user = mock(IUser.class);
//        when(user.getId()).thenReturn(1L);
//
//        String name = "test.txt";
//        String originalFileName = "test.txt";
//        String contentType = "text/plain";
//        byte[] content = "Hello World".getBytes();
//
//        MockMultipartFile multipartFile = new MockMultipartFile(name, originalFileName, contentType, content);
//        String userFolderName = "UserId_" + user.getId();
//        Path userFolderPath = Paths.get(properties.getUploadDir(), "users", userFolderName);
//
//        StorageService storageService = new StorageService(properties);
//
//        // Act
//        storageService.storeUserProfilePicture(user, multipartFile);
//
//        // Assert
//        assertTrue(Files.exists(userFolderPath.resolve(multipartFile.getOriginalFilename())));
//        assertEquals("Hello World", new String(Files.readAllBytes(userFolderPath.resolve(multipartFile.getOriginalFilename()))));
//    }
//}
