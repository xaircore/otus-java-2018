package com.xairlab.otus.message_system.messages;

import com.xairlab.otus.message_system.entity.Address;
import com.xairlab.otus.message_system.entity.DBService;
import com.xairlab.otus.message_system.entity.MsgToDB;
import com.xairlab.otus.message_system.entity.User;

import java.util.List;

public class MsgSaveUser extends MsgToDB {

    private final String name;
    private final int age;

    public MsgSaveUser(Address from, Address to, String name, int age) {
        super(from, to);
        this.name = name;
        this.age = age;
    }

    @Override
    public void exec(DBService dbService) {
        User user = dbService.saveUser(name, age);
        dbService.getMessageSystem().sendMessage(new MsgSaveUserAnswer(getTo(), getFrom(), user));
    }
}
