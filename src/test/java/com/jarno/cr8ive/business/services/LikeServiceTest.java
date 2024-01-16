package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.ILikeRepository;
import com.jarno.cr8ive.business.boundaries.repository.IPostRepository;
import com.jarno.cr8ive.business.model.request.like.LikeRequestModel;
import com.jarno.cr8ive.business.model.response.like.UpdateLikeCounterResponseModel;
import com.jarno.cr8ive.domain.Content;
import com.jarno.cr8ive.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class LikeServiceTest {
    @InjectMocks
    private LikeService likeService;
    @Mock
    private IPostRepository repoMock;

    @Mock
    private ILikeRepository likeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLikesForPosts() {
        // ARRANGE
        List<Post> posts = new ArrayList<>();
        Post post1 = createPostWithAmountLikes(1L, 10L);
        Post post2 = createPostWithAmountLikes(2L, 5L);
        posts.add(post1);
        posts.add(post2);

        when(repoMock.getLikeCount(1L)).thenReturn(10L);
        when(repoMock.getLikeCount(2L)).thenReturn(5L);

        // ACT
        likeService.getLikesForPosts(posts);

        // ASSERT
        assertEquals(10L, post1.getLikes());
        assertEquals(5L, post2.getLikes());
    }

    @Test
    void testGetLikesForOptionalPost() {
        // ARRANGE
        Post post = createPostWithAmountLikes(1, 8);
        Optional<Post> postOptional = Optional.of(post);

        when(repoMock.getLikeCount(1L)).thenReturn(8L);

        // ACT
        likeService.getLikesForOptionalPost(postOptional);

        // ASSERT
        assertEquals(8L, post.getLikes());
    }


    @Test
    void testUpdateLikeCounter_Success() {
        // ARRANGE
        LikeRequestModel requestModel = new LikeRequestModel(1L, true);
        Post post = createPostWithAmountLikes(1L, 11);
        Optional<Post> postOptional = Optional.of(post);

        when(repoMock.findByPostId(1L)).thenReturn(postOptional);
        when(likeRepository.ValidateIfPostLiked(123L, 1L)).thenReturn(true);
        when(repoMock.getLikeCount(1L)).thenReturn(12L);

        // ACT
        UpdateLikeCounterResponseModel response = likeService.update(requestModel, post.getUserId());

        // ASSERT
        assertEquals(12L, response.getLikeCount());
    }

    @Test
    void testValidatePostLiked_True() {
        // ARRANGE
        long userId = 123L;
        long postId = 1L;

        when(likeRepository.ValidateIfPostLiked(userId, postId)).thenReturn(true);

        // ACT
        boolean isPostLiked = likeService.validatePostLiked(userId, postId);

        // ASSERT
        assertTrue(isPostLiked);
    }

    private  Post createPostWithAmountLikes(long id, long likes) {
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
        return new Post(id, contentList ,"mock description", date, likes, 0, hashtagIds, userId );
    }
}
