package com.desire323.chat.service;

import com.desire323.chat.entity.ChatMessage;
import com.desire323.chat.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void saveMessage(ChatMessage message) {
        Optional<UUID> optionalConversationId;
        if (message.getSenderId() > message.getReceiverId()) {
            optionalConversationId = chatRepository.getConversationId(message.getReceiverId(), message.getSenderId());
        } else {
            optionalConversationId = chatRepository.getConversationId(message.getSenderId(), message.getReceiverId());
        }
        UUID conversationId;
        if (optionalConversationId.isEmpty()) {
            conversationId = UUID.randomUUID();
        } else {
            conversationId = optionalConversationId.get();
        }

        message.setConversationId(conversationId);
        chatRepository.saveMessage(message);
    }

    public Optional<UUID> getConversationId(int senderId, int receiverId) {
        if (senderId == receiverId) {
            return Optional.empty();
        }
        if (receiverId < senderId) {
            int temp = senderId;
            senderId = receiverId;
            receiverId = temp;
        }
        Optional<UUID> optionalConversationId = chatRepository.getConversationId(senderId, receiverId);
        if (optionalConversationId.isEmpty()) {
            UUID conversationId = UUID.randomUUID();
            chatRepository.setConversationId(senderId, receiverId, conversationId);
            chatRepository.setConversationId(receiverId, senderId, conversationId);
            return Optional.of(conversationId);
        }
        return optionalConversationId;
    }

    public Optional<UUID> getConversationId(String senderIdStr, String receiverIdStr) {
        int senderId = Integer.parseInt(senderIdStr);
        int receiverId = Integer.parseInt(receiverIdStr);

        return getConversationId(senderId, receiverId);
    }

    public List<ChatMessage> getAllMessages(String conversationId) {
        List<ChatMessage> messages = chatRepository.getMessagesByConversationId(UUID.fromString(conversationId));
        return messages;
    }

    public Optional<ChatMessage> getLastMessage(String conversationId) {
        return chatRepository.getLastMessage(UUID.fromString(conversationId));

    }
}
