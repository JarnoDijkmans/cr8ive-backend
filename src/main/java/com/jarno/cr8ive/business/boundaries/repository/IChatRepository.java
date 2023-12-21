package com.jarno.cr8ive.business.boundaries.repository;

import com.jarno.cr8ive.domain.Message;

import java.util.List;

public interface IChatRepository {
    List<Message> getMessagesBetweenUsers(long senderId, long receiverId);
    void saveMessage(Message message);
}
