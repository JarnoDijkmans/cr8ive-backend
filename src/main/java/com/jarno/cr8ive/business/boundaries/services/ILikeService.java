package com.jarno.cr8ive.business.boundaries.services;

import com.jarno.cr8ive.business.model.request.like.LikeRequestModel;
import com.jarno.cr8ive.business.model.response.like.UpdateLikeCounterResponseModel;

public interface ILikeService {
     UpdateLikeCounterResponseModel update(LikeRequestModel requestModel, long userId);

     boolean validatePostLiked(long userId, long postId);
}
