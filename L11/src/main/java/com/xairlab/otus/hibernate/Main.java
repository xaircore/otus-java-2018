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

        User user1 = Utils.createUser( "James Bond", 25, "Ivanova 7", Arrays.asList("89092785110", "89109536603"));
        User user2 = Utils.createUser( "Alex Finch", 19, "Backer Street 19", Arrays.asList("89098521385"));
        User user3 = Utils.createUser("Steve Mozart", 45, "Las Vegas", Arrays.asList("89092785110", "89104780928", "89102970928"));
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);

        user3.setName("Frank Herbert");
        userService.save(user3);

        User herbert = userService.load(user3.getId());
        System.out.println(herbert.getName());

        h2con.close();
    }
}
