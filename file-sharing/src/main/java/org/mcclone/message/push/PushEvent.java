package org.mcclone.message.push;

import org.springframework.context.ApplicationEvent;

/**
 * @author McClone
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
