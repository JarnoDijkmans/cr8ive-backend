package com.jarno.cr8ive.controller;

import lombok.Data;

@Data
public class ChatMessage {
    private String senderId ;
    private String receiverId;
    private String text;
}
