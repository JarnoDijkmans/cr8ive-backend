package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.output.IPostGateway;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreatePostResponseModel;
import com.jarno.cr8ive.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceTest {

    private PostService postService;
    private IPostGateway gateway;


    @BeforeEach
    public void setUp() {
        gateway = Mockito.mock(IPostGateway.class);
        StorageService storageService = Mockito.mock(StorageService.class);
        postService = new PostService(gateway, storageService);
    }

    @Test
    void testCreatePost_Success() throws PostCustomException {
        // ARRANGE
        MultipartFile sampleFile = createSampleMultipartFile();

        List<MultipartFile> contentList = new ArrayList<>();
        contentList.add(sampleFile);

        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);
        hashtagIds.add(2);

        CreatePostRequestModel requestModel = new CreatePostRequestModel(contentList, "This is a description", hashtagIds, 1);
        long expectedResult = 1L;
        Mockito.when(gateway.save(Mockito.any(Post.class))).thenReturn(expectedResult);

        // ACT
        CreatePostResponseModel responseModel = postService.create(requestModel);

        // ASSERT
        Mockito.verify(gateway).save(Mockito.any(Post.class));
        assertEquals(expectedResult, responseModel.getId());
    }


    @Test
     void testCreatePost_Fail_ContentInvalid() {
        // ARRANGE
        List<MultipartFile> contentList = new ArrayList<>();
        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);
        hashtagIds.add(2);
        CreatePostRequestModel requestModel = new CreatePostRequestModel(contentList, "This is a description", hashtagIds, 1);

        // ACT & ASSERT
        assertThrows(PostCustomException.class, () -> postService.create(requestModel));
    }



    private MultipartFile createSampleMultipartFile() {
        return new MockMultipartFile("file", "sample-image.jpg", "image/jpeg", "Sample Image Content".getBytes());
    }
}