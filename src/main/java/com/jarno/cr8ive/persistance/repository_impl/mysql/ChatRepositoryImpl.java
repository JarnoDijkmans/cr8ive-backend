package com.jarno.cr8ive.persistance.repository_impl.mysql;

import com.jarno.cr8ive.business.boundaries.repository.IChatRepository;
import com.jarno.cr8ive.domain.Message;
import com.jarno.cr8ive.persistance.converter.ChatConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.MessageJpaMapper;
import com.jarno.cr8ive.persistance.repository_jpa.JpaChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ChatRepositoryImpl implements IChatRepository {
    private final JpaChatRepository chatRepository;
    private final ChatConverter converter;

    @Autowired
    public ChatRepositoryImpl(JpaChatRepository messageRepository, ChatConverter converter) {
        this.chatRepository = messageRepository;
        this.converter = converter;
    }

    public List<Message> getMessagesBetweenUsers(long user1, long user2) {
        List<MessageJpaMapper> messageJpaMappers = chatRepository.findMessagesBetweenUsers(user1, user2);
        return converter.toMessages(messageJpaMappers);
    }

    public void saveMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        MessageJpaMapper messageJpaMapper = converter.toJpaMapper(message);
        chatRepository.save(messageJpaMapper);
    }
}
