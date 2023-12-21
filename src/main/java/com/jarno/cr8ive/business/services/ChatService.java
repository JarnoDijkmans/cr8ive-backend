package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.IChatRepository;
import com.jarno.cr8ive.business.boundaries.services.IChatService;
import com.jarno.cr8ive.business.model.request.chat.SaveMessageRequestModel;
import com.jarno.cr8ive.business.model.response.chat.MessagesResponseModel;
import com.jarno.cr8ive.domain.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatService implements IChatService {
    IChatRepository repository;

    @Override
    public MessagesResponseModel getMessagesBetweenUsers(long senderId, long receiverId) {
        try{
            List<Message> messages = repository.getMessagesBetweenUsers(senderId, receiverId);
            return new MessagesResponseModel(messages);
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public void saveMessage(SaveMessageRequestModel requestModel) {
        try{
            Message message = Message.builder()
                    .senderId(requestModel.getSenderId())
                    .receiverId(requestModel.getReceiverId())
                    .content(requestModel.getContent())
                    .build();
            repository.saveMessage(message);
        }catch (Exception e){
            throw e;
        }
    }
}
