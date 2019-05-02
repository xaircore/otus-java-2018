package com.xairlab.otus.message_system.messages;

import com.xairlab.otus.message_system.entity.Address;
import com.xairlab.otus.message_system.entity.Addressee;

public abstract class Message {

    private final Address from;
    private final Address to;

    public Message(Address from, Address to) {
        this.from = from;
        this.to = to;
    }

    public Address getFrom() {
        return from;
    }

    public Address getTo() {
        return to;
    }

    public abstract void exec(Addressee addressee);
}
