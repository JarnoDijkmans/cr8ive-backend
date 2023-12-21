package com.jarno.cr8ive.persistance.converter;

import com.jarno.cr8ive.domain.Message;
import com.jarno.cr8ive.persistance.repository_impl.entity.MessageJpaMapper;

import java.util.List;

public interface ChatConverter {
    MessageJpaMapper toJpaMapper(Message message);
    List<Message> toMessages(List<MessageJpaMapper> messageJpaMapperList);
}
