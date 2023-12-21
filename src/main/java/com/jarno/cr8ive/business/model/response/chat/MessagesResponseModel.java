package com.jarno.cr8ive.business.model.response.chat;

import com.jarno.cr8ive.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessagesResponseModel {
    List<Message> messages;
}
