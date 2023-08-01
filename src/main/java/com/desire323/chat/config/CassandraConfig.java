package com.desire323.chat.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;


@Configuration
public class CassandraConfig {

    private final String username;
    private final String password;
    private final String keyspace;
    private final String contactPoint;
    private final int port;
    private final String datacenter;

    public CassandraConfig() {
        this.username = System.getenv("CASSANDRA_USERNAME");
        this.password = System.getenv("CASSANDRA_PASSWORD");
        this.keyspace = System.getenv("CASSANDRA_KEYSPACE");
        this.contactPoint = System.getenv("CASSANDRA_CONTACT_POINT");
        this.port = Integer.parseInt(System.getenv("CASSANDRA_PORT"));
        this.datacenter = System.getenv("CASSANDRA_LOCAL_DC");
    }


    @Bean
    public CqlSession cqlSession() {

        return CqlSession.builder()
                .withAuthCredentials(username, password)
                .withKeyspace(keyspace)
                .withLocalDatacenter(datacenter)
                .addContactPoint(new InetSocketAddress(contactPoint, port))
                .build();
    }
}