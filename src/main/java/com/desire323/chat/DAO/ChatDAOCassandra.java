package com.desire323.chat.DAO;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.desire323.chat.entity.ChatMessage;
import com.desire323.chat.repository.ChatRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ChatDAOCassandra implements ChatRepository {
    private final CqlSession cqlSession;

    public ChatDAOCassandra(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
    }

    public void saveMessage(ChatMessage message) {
        PreparedStatement psChat = cqlSession.prepare(
                "INSERT INTO chat (conversation_id, sender_id, receiver_id, timestamp, message) VALUES (?, ?, ?, ?, ?)");
        BoundStatement boundStatementChat = psChat.bind(message.getConversationId(), message.getSenderId(), message.getReceiverId(), Instant.now(), message.getMessage());
        cqlSession.execute(boundStatementChat);

    }

    public void setConversationId(int senderId, int receiverId, UUID conversationId) {
        PreparedStatement psLookup = cqlSession.prepare(
                "INSERT INTO conversation_lookup (sender_id, receiver_id, conversation_id) VALUES (?, ?, ?)");
        BoundStatement boundStatementLookup = psLookup.bind(senderId, receiverId, conversationId);
        cqlSession.execute(boundStatementLookup);
    }

    @Override
    public Optional<UUID> getConversationId(int senderId, int receiverId) {
        PreparedStatement ps = cqlSession.prepare(
                "SELECT conversation_id FROM conversation_lookup WHERE sender_id = ? AND receiver_id = ?");
        BoundStatement boundStatement = ps.bind(senderId, receiverId);
        Row row = cqlSession.execute(boundStatement).one();

        return row != null ? Optional.of(row.getUuid("conversation_id")) : Optional.empty();
    }

    public List<ChatMessage> getMessagesByConversationId(UUID conversationId) {
        PreparedStatement ps = cqlSession.prepare(
                "SELECT * FROM chat WHERE conversation_id = ?");
        BoundStatement boundStatement = ps.bind(conversationId);
        return cqlSession.execute(boundStatement).all().stream().map(row -> {
            System.out.println("Row data: " + row);
            ChatMessage message = new ChatMessage(
                    row.getUuid("conversation_id"),
                    row.getInt("sender_id"),
                    row.getInt("receiver_id"),
                    row.getInstant("timestamp"),
                    row.getString("message")
            );
            System.out.println("Mapped message: " + message);
            return message;
        }).collect(Collectors.toList());
    }
}


