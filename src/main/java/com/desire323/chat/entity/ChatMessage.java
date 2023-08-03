package com.desire323.chat.entity;

import java.time.Instant;
import java.util.UUID;

public class ChatMessage {
    private UUID conversationId;
    private int senderId;
    private int receiverId;
    private Instant timestamp;
    private String message;

    public ChatMessage(UUID conversationId, int senderId, int receiverId, String message) {
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
    }

    public ChatMessage() {
    }

    public ChatMessage(UUID conversationId, int senderId, int receiverId, Instant timestamp, String message) {
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
        this.message = message;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public void setConversationId(UUID conversationId) {
        this.conversationId = conversationId;
    }

    public int getSenderId() {
        return senderId;
    }
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
    public int getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

