package com.xairlab.otus.message_system.messages;

import com.xairlab.otus.message_system.entity.Address;
import com.xairlab.otus.message_system.entity.Addressee;
import com.xairlab.otus.message_system.entity.DBService;

public abstract class MsgToDB extends Message {

    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            exec((DBService) addressee);
        }
    }

    public abstract void exec(DBService dbService);
}
