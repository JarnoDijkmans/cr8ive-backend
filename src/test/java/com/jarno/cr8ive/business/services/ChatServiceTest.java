package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.IChatRepository;
import com.jarno.cr8ive.business.model.request.chat.SaveMessageRequestModel;
import com.jarno.cr8ive.business.model.response.chat.MessagesResponseModel;
import com.jarno.cr8ive.domain.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ChatServiceTest {
    @Mock
    IChatRepository chatRepository;

    @InjectMocks
    ChatService chatService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testGetMessagesBetweenUsers() {
        List<Message> mockMessages = Arrays.asList(
                new Message(1L, 2L, "Hello", LocalDateTime.now()),
                new Message(2L, 1L, "Hi", LocalDateTime.now())
        );
        when(chatRepository.getMessagesBetweenUsers(1L, 2L)).thenReturn(mockMessages);

        MessagesResponseModel response = chatService.getMessagesBetweenUsers(1L, 2L);

        verify(chatRepository, times(1)).getMessagesBetweenUsers(1L, 2L);
        assertEquals(2, response.getMessages().size());
        assertEquals("Hello", response.getMessages().get(0).getContent());
        assertEquals("Hi", response.getMessages().get(1).getContent());
    }

    @Test
    public void testGetMessagesBetweenUsersThrowsException() {
        when(chatRepository.getMessagesBetweenUsers(1L, 2L)).thenThrow(new RuntimeException("Test exception"));

        assertThrows(RuntimeException.class, () -> chatService.getMessagesBetweenUsers(1L, 2L));
    }

    @Test
     void testSaveMessage() {
        SaveMessageRequestModel requestModel = new SaveMessageRequestModel();
        requestModel.setSenderId(1L);
        requestModel.setReceiverId(2L);
        requestModel.setContent("Test Message");

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        doNothing().when(chatRepository).saveMessage(messageCaptor.capture());

        chatService.saveMessage(requestModel);

        verify(chatRepository, times(1)).saveMessage(any(Message.class));
        Message savedMessage = messageCaptor.getValue();
        assertEquals(requestModel.getSenderId(), savedMessage.getSenderId());
        assertEquals(requestModel.getReceiverId(), savedMessage.getReceiverId());
        assertEquals(requestModel.getContent(), savedMessage.getContent());
    }

    @Test
     void testSaveMessageThrowsException() {
        SaveMessageRequestModel requestModel = new SaveMessageRequestModel();
        requestModel.setSenderId(1L);
        requestModel.setReceiverId(2L);
        requestModel.setContent("Test Message");

        doThrow(new RuntimeException("Test exception")).when(chatRepository).saveMessage(any(Message.class));

        assertThrows(RuntimeException.class, () -> chatService.saveMessage(requestModel));
    }
}
