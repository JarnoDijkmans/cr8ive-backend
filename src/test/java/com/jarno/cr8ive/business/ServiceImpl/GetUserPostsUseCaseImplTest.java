package com.jarno.cr8ive.business.ServiceImpl;

import com.jarno.cr8ive.business.interfaces.PostRepository;
import com.jarno.cr8ive.business.exception.UserIdDoesntExistsException;
import com.jarno.cr8ive.business.interfaces.PostServiceInterface;
import com.jarno.cr8ive.domain.Post;
import org.junit.jupiter.api.Test;
import com.jarno.cr8ive.persistence.Impl.FakePostRepositoryImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetUserPostsUseCaseImplTest {
    /**
     * @verifies return user posts when user Exist.
     * @see PostService#getUserPosts(long)
     */
    @Test
    public void getUserPosts_shouldReturnUserPostsWhenUserExist() throws Exception {
        //Arrange
        long userId = 1L;
        List<Post> expectedPosts = createSampleUserPosts(userId);
        PostRepository postRepository = new FakePostRepositoryImpl();
        StorageService storageService = new StorageService();
        expectedPosts.forEach(postRepository::save);
        PostServiceInterface sut = new PostService (postRepository, storageService);

        //Act
        List<Post> actualPosts = sut.getUserPosts(userId);

        //Assert
        assertEquals(expectedPosts, actualPosts);
    }

    /**
     * @verifies return throw exception when user doenst exist.
     * @see PostService#getUserPosts(long)
     */
    @Test
    public void getUserPosts_shouldReturnThrowExceptionWhenUserDoenstExist() throws Exception {
        // Arrange
        long userId = 1L;

        PostRepository postRepository = mock(PostRepository.class);
        StorageService storageService = new StorageService();
        PostServiceInterface sut = new PostService (postRepository, storageService);

        // Act and Assert
        assertThrows(UserIdDoesntExistsException.class, () -> {
            sut.getUserPosts(userId);
        });
    }
    //test
    private List<Post> createSampleUserPosts(long userId) {
        List<Post> posts = new ArrayList<>();
        posts.add(createSamplePost(userId, 1));
        posts.add(createSamplePost(userId, 2));
        return posts;
    }

    private Post createSamplePost(long userId, long postId) {

        MultipartFile sampleFile = createSampleMultipartFile();

        List<MultipartFile> contentList = new ArrayList<>();
        contentList.add(sampleFile);

        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);
        hashtagIds.add(2);

        return Post.builder()
                .id(postId)
                .userId(userId)
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
}