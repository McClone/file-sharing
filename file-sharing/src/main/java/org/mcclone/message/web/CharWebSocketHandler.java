package org.mcclone.message.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author McClone
 */
@Slf4j
public class CharWebSocketHandler extends TextWebSocketHandler {

    //持有所有session
    private Map<String, WebSocketSession> socketSessionHolder = new ConcurrentSkipListMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        for (WebSocketSession socketSession : socketSessionHolder.values()) {
            if (session.getId().equals(socketSession.getId())) continue;
            try {
                socketSession.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        socketSessionHolder.put(session.getId(), session);
        log.info(session.getPrincipal().toString().concat("创建WebSocketSession"));
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        socketSessionHolder.remove(session.getId());
        log.info(session.getPrincipal().toString().concat("删除WebSocketSession"));
        super.afterConnectionClosed(session, status);
    }
}
