package com.xairlab.otus.message_system.messages;

import com.xairlab.otus.message_system.entity.Address;
import com.xairlab.otus.message_system.entity.FrontendService;
import com.xairlab.otus.message_system.entity.User;

public class MsgSaveUserAnswer extends MsgToFrontend {
    private final User user;

    public MsgSaveUserAnswer(Address from, Address to, User user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.saveUserHandler(user);
    }
}
