package com.xairlab.otus.hibernate;

import com.xairlab.otus.hibernate.connection.H2Connection;
import com.xairlab.otus.hibernate.entity.User;
import com.xairlab.otus.hibernate.service.DBService;
import com.xairlab.otus.hibernate.service.UserService;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        H2Connection h2con = new H2Connection();
        DBService<User> DBService = new DBService<>(h2con);

        UserService userService = new UserService(DBService);

        User user1 = Utils.createUser(7, "James Bond", 25, "Ivanova 7", Arrays.asList("89092785110", "89109536603"));
        User user2 = Utils.createUser(1, "Alex Finch", 19, "Backer Street 19", Arrays.asList("89098521385"));
        User user3 = Utils.createUser(3, "Steve Mozart", 45, "Las Vegas", Arrays.asList("89092785110", "89104780928", "89102970928"));
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);

        User user4 = Utils.createUser(3, "Frank Herbert", 60, "England", Arrays.asList("443556"));
        userService.save(user4);

        User herbert = userService.load(3);
        System.out.println(herbert);

        h2con.close();
    }
}
