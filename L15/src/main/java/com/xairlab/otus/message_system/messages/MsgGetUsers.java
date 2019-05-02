package com.xairlab.otus.message_system.messages;

import com.xairlab.otus.message_system.entity.Address;
import com.xairlab.otus.message_system.entity.DBService;
import com.xairlab.otus.message_system.entity.User;

import java.util.List;

public class MsgGetUsers extends MsgToDB {

    public MsgGetUsers(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(DBService dbService) {
        List<User> users = dbService.getUsers();
        dbService.getMessageSystem().sendMessage(new MsgGetUsersAnswer(getTo(), getFrom(), users));
    }
}
