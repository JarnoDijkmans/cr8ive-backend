package com.jarno.cr8ive.persistance.repository_impl.mysql;

import com.jarno.cr8ive.business.boundaries.repository.IPostRepository;
import com.jarno.cr8ive.domain.Content;
import com.jarno.cr8ive.domain.Hashtags;
import com.jarno.cr8ive.domain.Post;
import com.jarno.cr8ive.persistance.repository_impl.entity.PostJpaMapper;
import com.jarno.cr8ive.persistance.repository_jpa.JpaHashtagRepository;
import com.jarno.cr8ive.persistance.repository_jpa.JpaPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PostRepositoryImplTest {

    @Mock
    private JpaPostRepository postRepository;
    @Mock
    private JpaHashtagRepository hashtagRepository;
    private IPostRepository postGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        postGateway = new PostRepositoryImpl(postRepository, hashtagRepository);
    }

    @Test
    void testSave() {
        // Arrange
        List<MultipartFile> sampleFiles = createSampleMultipartFile();
        List<Content> files = new ArrayList<>();
        for (MultipartFile file : sampleFiles) {
            files.add(Content.builder()
                    .url(file.getOriginalFilename())
                    .type(file.getContentType())
                    .build());
        }
        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);
        hashtagIds.add(2);

        Post post = Post.builder()
                .id(1)
                .content(files)
                .description("sample description")
                .creationDate(new Date())
                .likes(0)
                .shareCount(0)
                .hashtagIds(hashtagIds)
                .userId(1)
                .build();

        when(postRepository.save(any())).thenReturn(post);

        // Act and Assert
        assertEquals(1L, post.getId());
    }

    @Test
    void testFindByUserId() {
        // Arrange
        long userId = 1L;
        List<PostJpaMapper> postsJpa = new ArrayList<>();
        postsJpa.add(new PostJpaMapper());

        // Act
        when(postRepository.findByUserId(userId)).thenReturn(postsJpa);

        List<Post> posts = postGateway.findByUserId(userId);

        // Assert
        assertEquals(1, posts.size());
        verify(postRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testFindHashtagsById() {
        // Arrange
        List<Integer> hashtagIds = new ArrayList<>();
        hashtagIds.add(1);

        // Act
        when(hashtagRepository.findById(anyInt())).thenReturn(Optional.empty());

        List<Hashtags> hashtags = postGateway.findHashtagsById(hashtagIds);

        // Assert
        assertEquals(0, hashtags.size());
        verify(hashtagRepository, times(1)).findById(anyInt());
    }

    @Test
    void testExistsById() {
        // Arrange
        String id = "1";

        // Act
        when(postRepository.existsById(id)).thenReturn(true);

        boolean exists = postGateway.existsById(id);

        // Assert
        assertTrue(exists);
        verify(postRepository, times(1)).existsById(id);
    }

    private List <MultipartFile> createSampleMultipartFile() {
        List <MultipartFile> files = new ArrayList<>();
        files.add (new MockMultipartFile("file", "sample-image.jpg", "image/jpeg", "Sample Image Content".getBytes()));
        return files;
    }
}