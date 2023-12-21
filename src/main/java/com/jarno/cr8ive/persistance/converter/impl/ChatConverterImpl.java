package com.jarno.cr8ive.persistance.converter.impl;

import com.jarno.cr8ive.domain.Message;
import com.jarno.cr8ive.persistance.converter.ChatConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.MessageJpaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatConverterImpl implements ChatConverter {
    public MessageJpaMapper toJpaMapper(Message message) {
        return MessageJpaMapper.builder()
                .content(message.getContent())
                .receiverId(message.getReceiverId())
                .senderId(message.getSenderId())
                .timestamp(message.getTimestamp())
                .build();
    }

    public List<Message> toMessages(List<MessageJpaMapper> messageJpaMapper) {
        return messageJpaMapper.stream()
                .map(jpaMessage -> Message.builder()
                        .content(jpaMessage.getContent())
                        .receiverId(jpaMessage.getReceiverId())
                        .senderId(jpaMessage.getSenderId())
                        .timestamp(jpaMessage.getTimestamp())
                        .build())
                .toList();
    }
}

