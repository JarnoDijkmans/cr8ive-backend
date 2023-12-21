package com.jarno.cr8ive.controller;

import com.jarno.cr8ive.business.boundaries.services.IAuthService;
import com.jarno.cr8ive.business.boundaries.services.IChatService;
import com.jarno.cr8ive.business.model.request.chat.SaveMessageRequestModel;
import com.jarno.cr8ive.business.model.response.chat.MessagesResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chat")
public class ChatController {
    private final IChatService chatService;
    private final IAuthService authService;

    @Autowired
    public ChatController(IChatService chatService, IAuthService authService) {
        this.chatService = chatService;
        this.authService = authService;
    }
    @GetMapping("{receiverId}")
    public MessagesResponseModel getMessagesBetweenUsers(@RequestHeader("Authorization") String sender, @PathVariable long receiverId) {
        String token = extractTokenFromAuthorizationHeader(sender);
        long senderId = authService.extractUserIdFromToken(token);
        return chatService.getMessagesBetweenUsers(senderId, receiverId);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveMessage(@RequestHeader("Authorization") String sender, @RequestBody SaveMessageRequestModel requestModel) {
        String token = extractTokenFromAuthorizationHeader(sender);
        long senderId = authService.extractUserIdFromToken(token);
        requestModel.setSenderId(senderId);

        chatService.saveMessage(requestModel);
        return ResponseEntity.ok("Message sent successfully");
    }

    private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        return authorizationHeader.replaceFirst("Bearer ", "").trim();
    }
}

