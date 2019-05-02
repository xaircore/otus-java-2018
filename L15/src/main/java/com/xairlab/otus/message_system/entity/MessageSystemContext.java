package com.xairlab.otus.message_system.entity;

import org.springframework.stereotype.Service;

@Service
public class MessageSystemContext {

    private final MessageSystem messageSystem;
    private Address frontAddress;
    private Address dbAddress;

    public MessageSystemContext() {
        messageSystem = new MessageSystem();
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getFrontAddress() {
        return frontAddress;
    }

    public void setFrontAddress(Address frontAddress) {
        this.frontAddress = frontAddress;
    }

    public Address getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(Address dbAddress) {
        this.dbAddress = dbAddress;
    }
}
