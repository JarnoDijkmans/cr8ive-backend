package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.output.register.IPostRegisterGateway;
import com.jarno.cr8ive.business.converter.CreatePostConverter;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import com.jarno.cr8ive.business.presenter.IPostPresenter;
import com.jarno.cr8ive.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

class PostServiceTest {

    private PostService postService;
    private IPostRegisterGateway gateway;
    private StorageService storageService;
    private IPostPresenter presenter;


    @BeforeEach
    public void setUp() {
        gateway = Mockito.mock(IPostRegisterGateway.class);
        storageService = Mockito.mock(StorageService.class);
        presenter = Mockito.mock(IPostPresenter.class);
        postService = new PostService(presenter, gateway, storageService);
    }

    @Test
    public void testCreatePost_Success() throws PostCustomException {
        // ARRANGE
        MultipartFile sampleFile = createSampleMultipartFile();

        List<MultipartFile> contentList = new ArrayList<>();
        contentList.add(sampleFile);

        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);
        hashtagIds.add(2);

        CreatePostRequestModel requestModel = new CreatePostRequestModel(contentList, "This is a description", hashtagIds, 1);
        Post post = CreatePostConverter.toPost(requestModel);
        long expectedResult = 1L;

        // ACT
        Mockito.when(gateway.save(Mockito.any(Post.class))).thenReturn(expectedResult);


        // ASSERT
        CreatePostResponseModel responseModel = postService.create(requestModel);
        Mockito.verify(presenter).prepareSuccessView(Mockito.any(CreatePostResponseModel.class));
    }


    @Test
    public void testCreatePost_Fail_ContentInvalid() throws PostCustomException {
        // ARRANGE
        List<MultipartFile> contentList = new ArrayList<>();

        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);
        hashtagIds.add(2);

        CreatePostRequestModel requestModel = new CreatePostRequestModel(contentList, "This is a description", hashtagIds, 1);
        Post post = CreatePostConverter.toPost(requestModel);

        // ACT
        Mockito.when(gateway.save(Mockito.any(Post.class))).thenReturn(0L);

        // ASSERT
        CreatePostResponseModel responseModel = postService.create(requestModel);
        Mockito.verify(presenter).prepareFailView(Mockito.any(PostCustomException.class));
    }



    private MultipartFile createSampleMultipartFile() {
        MockMultipartFile mockFile = new MockMultipartFile("file", "sample-image.jpg", "image/jpeg", "Sample Image Content".getBytes());
        return mockFile;
    }


    @Test
    void findByUserId() {
    }
}