package com.jarno.cr8ive.business.model.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveMessageRequestModel {
    long senderId;
    long receiverId;
    String content;
}
