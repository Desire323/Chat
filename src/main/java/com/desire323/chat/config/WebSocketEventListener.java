//package com.desire323.chat.config;
//
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionConnectedEvent;
//
//@Component
//public class WebSocketEventListener {
//
//    public WebSocketEventListener() {
//
//    }
//
//    @EventListener
//    public void handleWebSocketDisconnectListener(
//            SessionConnectedEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        int username = (int) headerAccessor.getSessionAttributes().get("username");
//        if (username != 0) {
//            System.out.println("User Disconnected : " + username);
//        }
//    }
//}
