package com.xairlab.otus.message_system.messages;

import com.xairlab.otus.message_system.entity.Address;
import com.xairlab.otus.message_system.entity.Addressee;
import com.xairlab.otus.message_system.entity.FrontendService;

public abstract class MsgToFrontend extends Message {

    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        }
    }

    public abstract void exec(FrontendService frontendService);
}