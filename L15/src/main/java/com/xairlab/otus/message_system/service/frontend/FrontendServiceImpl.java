package com.xairlab.otus.message_system.service.frontend;


import com.xairlab.otus.message_system.entity.*;
import com.xairlab.otus.message_system.messages.MsgGetUsers;
import com.xairlab.otus.message_system.messages.MsgSaveUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FrontendServiceImpl implements FrontendService {

    private final Address address = new Address("Frontend");
    private final MessageSystemContext context;
    private List<User> users;

    public FrontendServiceImpl(MessageSystemContext context) {
        this.context = context;
        this.users = new ArrayList<>();
        init();
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
        context.setFrontAddress(this.address);
        context.getMessageSystem().start();
    }

    @Override
    public void getUsers() {
        Message message = new MsgGetUsers(getAddress(), context.getDbAddress());
        context.getMessageSystem().sendMessage(message);
    }

    @Override
    public void addUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public List<User> getFrontendUsers() {
        return users;
    }

    @Override
    public void saveUser(String name, int age) {
        Message message = new MsgSaveUser(getAddress(), context.getDbAddress(), name, age);
        context.getMessageSystem().sendMessage(message);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMessageSystem() {
        return context.getMessageSystem();
    }

}
