package com.desire323.chat.controller;

import com.desire323.chat.entity.ChatMessage;
import com.desire323.chat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/chat-utils")
public class ChatUtilsController {

    private final ChatService chatService;

    public ChatUtilsController(ChatService chatService) {
        this.chatService = chatService;
    }


    @GetMapping("/{receiverId}")
    public String getConversationId(@RequestHeader("x-auth-user-id") String senderId, @PathVariable String receiverId) {
        Optional<UUID> optionalUUID = chatService.getConversationId(senderId, receiverId);
        if (optionalUUID.isEmpty()) {
            return "";
        }
        return optionalUUID.get().toString();
    }

    @GetMapping("/messages/{conversationId}")
    public List<ChatMessage> getMessages(@PathVariable String conversationId) {
        return chatService.getAllMessages(conversationId);
    }

    @GetMapping("/messages/{conversationId}/last")
    public ResponseEntity<ChatMessage> getLastMessage(@PathVariable String conversationId) {
        Optional<ChatMessage> optionalLastMessage = chatService.getLastMessage(conversationId);
        if (optionalLastMessage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalLastMessage.get());
    }

}
