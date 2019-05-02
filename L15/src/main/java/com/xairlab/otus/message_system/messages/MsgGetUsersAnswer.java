package com.xairlab.otus.message_system.messages;


import com.xairlab.otus.message_system.entity.Address;
import com.xairlab.otus.message_system.entity.FrontendService;
import com.xairlab.otus.message_system.entity.User;

import java.util.List;

public class MsgGetUsersAnswer extends MsgToFrontend {
    private final List<User> users;

    public MsgGetUsersAnswer(Address from, Address to, List<User> users) {
        super(from, to);
        this.users = users;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.getUsersHandler(users);
    }
}
