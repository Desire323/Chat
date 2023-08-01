package com.desire323.chat.repository;

import com.desire323.chat.entity.ChatMessage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository {
    void saveMessage(ChatMessage message);
    void setConversationId(int senderId, int receiverId, UUID conversationId);
    Optional<UUID> getConversationId(int senderId, int receiverId);
    List<ChatMessage> getMessagesByConversationId(UUID conversationId);
}
