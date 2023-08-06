package com.desire323.chat.entity;

import java.time.Instant;
import java.util.UUID;

public class ChatMessage {
    private UUID conversationId;
    private int senderId;
    private int receiverId;
    private Instant timestamp;
    private String message;
    private String messageType;

    public ChatMessage(UUID conversationId, int senderId, int receiverId, String message, String messageType) {
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.messageType = messageType;
    }

    public ChatMessage() {
    }

    public ChatMessage(UUID conversationId, int senderId, int receiverId, Instant timestamp, String message, String messageType) {
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
        this.message = message;
        this.messageType = messageType;
    }

    // getters and setters

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

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
