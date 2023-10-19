package com.jarno.cr8ive.business.ServiceImpl;

import com.jarno.cr8ive.business.interfaces.PostRepository;
import com.jarno.cr8ive.business.interfaces.PostServiceInterface;
import com.jarno.cr8ive.business.request.post.CreatePostRequest;
import com.jarno.cr8ive.business.response.post.CreatePostResponse;
import org.junit.jupiter.api.Test;
import com.jarno.cr8ive.domain.Post;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CreatePostUseCaseImplTest {

    private CreatePostRequest createSamplePost() {

        MultipartFile sampleFile = createSampleMultipartFile();

        List<MultipartFile> contentList = new ArrayList<>();
        contentList.add(sampleFile);

        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);
        hashtagIds.add(2);

        return CreatePostRequest.builder()
                .userId(1)
                .content(contentList)
                .description("Sample Description")
                .likes(1L)
                .hashtagIds(hashtagIds)
                .build();
    }

    private Post createSamplePostWithId() {

        MultipartFile sampleFile = createSampleMultipartFile();

        List<MultipartFile> contentList = new ArrayList<>();
        contentList.add(sampleFile);

        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);
        hashtagIds.add(2);

        return Post.builder()
                .id(1)
                .userId(1)
                .content(contentList)
                .description("Sample Description")
                .likes(1L)
                .commandId(1)
                .hashtagIds(hashtagIds)
                .build();
    }


    private MultipartFile createSampleMultipartFile() {
        MockMultipartFile mockFile = new MockMultipartFile("file", "sample-image.jpg", "image/jpeg", "Sample Image Content".getBytes());
        return mockFile;
    }

    /**
     * @verifies return a response when a postInterfaces is valid
     * @see PostServiceInterface#createPost(CreatePostRequest)

     */
    @Test
    public void createPost_shouldReturnAResponseWhenAPostIsValid() throws Exception {
        // Arrange
        CreatePostRequest validRequest = createSamplePost();
        PostRepository postRepository = mock(PostRepository.class);
        StorageService storageService = mock(StorageService.class);
        when(postRepository.save(any())).thenReturn(createSamplePostWithId());

        PostServiceInterface sut = new PostService(postRepository, storageService);
        //Act
        CreatePostResponse createdPost = sut.createPost(validRequest);

        //Assert
        assertEquals(1L, createdPost.getId());
    }


}