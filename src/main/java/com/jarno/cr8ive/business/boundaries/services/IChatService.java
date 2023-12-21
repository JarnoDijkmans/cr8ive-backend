package com.jarno.cr8ive.business.boundaries.services;

import com.jarno.cr8ive.business.model.request.chat.SaveMessageRequestModel;
import com.jarno.cr8ive.business.model.response.chat.MessagesResponseModel;

public interface IChatService {
    MessagesResponseModel getMessagesBetweenUsers (long senderId, long receiverId);
    void saveMessage (SaveMessageRequestModel requestModel);
}
