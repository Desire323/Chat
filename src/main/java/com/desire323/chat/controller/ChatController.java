package com.desire323.chat.controller;

import com.desire323.chat.entity.ChatMessage;
import com.desire323.chat.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ChatController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage chat(ChatMessage chatMessage) throws Exception {
        chatService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/private-chat")
    public ChatMessage privateChat(ChatMessage chatMessage) {
        chatService.saveMessage(chatMessage);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + chatMessage.getConversationId().toString(), chatMessage);
        return chatMessage;
    }
}
