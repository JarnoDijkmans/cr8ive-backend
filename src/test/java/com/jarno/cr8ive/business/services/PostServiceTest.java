package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.IPostRepository;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.post.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetPostByPostIdResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetUserPostsResponseModel;
import com.jarno.cr8ive.domain.Content;
import com.jarno.cr8ive.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {
    @InjectMocks
    private PostService postService;
    @Mock
    private IPostRepository gatewayMock;

    @Mock
    private StorageService storageServiceMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        when(gatewayMock.save(Mockito.any(Post.class))).thenReturn(expectedResult);

        // ACT
        CreatePostResponseModel responseModel = postService.create(requestModel);

        // ASSERT
        verify(gatewayMock).save(Mockito.any(Post.class));
        assertEquals(expectedResult, responseModel.getId());
    }


    @Test
     void testCreatePost_Fail_ContentInvalid_NoFiles() {
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

    @Test
     void testFindByUserId_Success() throws PostCustomException {
        // Mocking repository behavior
        long userId = 123;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse("2021-12-01");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<Content> contentList = new ArrayList<>();
        Content content = Content.builder()
                        .url("test")
                        .type("test")
                        .build();
        contentList.add(content);
        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);
        hashtagIds.add(2);
        List<Post> mockPosts = new ArrayList<>();
        Post post = new Post(1, contentList ,"mock description", date, 0, 0, hashtagIds, userId );
        mockPosts.add(post);

        when(gatewayMock.findByUserId(userId)).thenReturn(mockPosts);

        // Calling the method to be tested
        GetUserPostsResponseModel response = postService.findByUserId(userId);

        // Assertions
        assertNotNull(response);
        assertEquals(mockPosts.size(), response.getPost().size());
        // Add more specific assertions based on your GetUserPostsResponseModel structure
    }

    @Test
     void testFindByUserId_Exception() {
        // Mocking repository to throw an exception
        long userId = 123; // Replace with a valid user ID for your test
        when(gatewayMock.findByUserId(userId)).thenThrow(new RuntimeException("Simulated exception"));

        // Assertions for exception handling
        assertThrows(PostCustomException.class, () -> {
            postService.findByUserId(userId);
        });
    }

    @Test
    void testDeleteUserPost_Success() {
        // ARRANGE
        long postId = 1L;

        doNothing().when(gatewayMock).deletePost(postId);
        doNothing().when(storageServiceMock).deletePost(postId);

        // ACT
        assertDoesNotThrow(() -> postService.deleteUserPost(postId));

        // ASSERT
        verify(gatewayMock, times(1)).deletePost(postId);
        verify(storageServiceMock, times(1)).deletePost(postId);
    }

    @Test
    void testFindByPostId_Success() throws PostCustomException {
        // ARRANGE
        long postId = 1L;
        Optional<Post> mockPost = Optional.of(new Post());
        when(gatewayMock.findByPostId(postId)).thenReturn(mockPost);

        // ACT
        GetPostByPostIdResponseModel response = postService.findByPostId(postId);

        // ASSERT
        assertNotNull(response);
    }

    @Test
    void testUserOwnsPost_Success() {
        // ARRANGE
        long postId = 1L;
        long userId = 123L;

        Post post = mock(Post.class);
        when(post.getUserId()).thenReturn(userId);

        Optional<Post> mockPost = Optional.of(post);
        IPostRepository gatewayMock = mock(IPostRepository.class);
        when(gatewayMock.findByPostId(postId)).thenReturn(mockPost);

        // ACT
        PostService postService = new PostService(gatewayMock, storageServiceMock);
        boolean isUserOwner = postService.userOwnsPost(postId, userId);

        // ASSERT
        assertTrue(isUserOwner);
    }


}
