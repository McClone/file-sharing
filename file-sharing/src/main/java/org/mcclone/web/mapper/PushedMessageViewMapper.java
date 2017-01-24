package org.mcclone.web.mapper;

import org.mcclone.domain.jpa.entity.PushedMessage;
import org.mcclone.web.ui.SimpleViewMapper;
import org.mcclone.web.view.PushedMessageView;
import org.springframework.stereotype.Component;

/**
 * Created by McClone on 2017/1/23.
 */
@Component("pushedMessageViewMapper")
public class PushedMessageViewMapper extends SimpleViewMapper<PushedMessage, PushedMessageView> {
    @Override
    public PushedMessageView mapping(PushedMessage pushedMessage) {
        PushedMessageView view = new PushedMessageView();
        view.setPushContent(pushedMessage.getPushContent());
        view.setCreatedDate(pushedMessage.getCreatedDate());
        view.setId(pushedMessage.getId());
        if (pushedMessage.getCreateUser() != null) {
            view.setCreateUsername(pushedMessage.getCreateUser().getUsername());
        }
        return view;
    }
}
