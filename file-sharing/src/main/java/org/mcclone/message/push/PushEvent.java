package org.mcclone.message.push;

import org.springframework.context.ApplicationEvent;

/**
 * Created by McClone on 2017/1/22.
 */
public class PushEvent extends ApplicationEvent {

    private String content;

    public PushEvent(Object source, String content) {
        super(source);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
