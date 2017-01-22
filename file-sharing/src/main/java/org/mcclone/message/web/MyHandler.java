package org.mcclone.message.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * Created by mcclone on 17-1-20.
 */
@Slf4j
public class MyHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info(message.getPayload());
        TextMessage sendMessage = new TextMessage("123456");
        try {
            session.sendMessage(sendMessage);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
