package org.mcclone.message.push;

import org.springframework.context.ApplicationEvent;

/**
 * Created by McClone on 2017/1/22.
 */
public class PushEvent extends ApplicationEvent {

    private String title;

    private String content;

    public PushEvent(Object source, String title, String content) {
        super(source);
        this.content = content;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
