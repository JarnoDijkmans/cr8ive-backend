package com.jarno.cr8ive.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
@Getter
@AllArgsConstructor
public class Message {
    private long messageId;
    private long senderId;
    private long recipientId;
    private String content;
    private Date timestamp;
}
