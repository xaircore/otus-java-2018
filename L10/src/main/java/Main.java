import com.xairlab.otus.jdbc.Utils;
import com.xairlab.otus.jdbc.connection.H2Connection;
import com.xairlab.otus.jdbc.entity.User;
import com.xairlab.otus.jdbc.service.H2Service;
import com.xairlab.otus.jdbc.service.UserService;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        H2Connection h2con = new H2Connection();
        H2Service<User> h2service = new H2Service<>(h2con);

        Utils utils = new Utils(h2con);
        utils.createTable();;

        UserService userService = new UserService(h2service);
        User user1 = Utils.createUser(7, "James Bond", 25);
        User user2 = Utils.createUser(1, "Alex Finch", 19);
        User user3 = Utils.createUser(3, "Steve Mozart", 45);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        utils.listTable();

        System.out.println();
        User user4 = Utils.createUser(3, "Frank Herbert", 60);
        userService.save(user4);
        utils.listTable();

        User herbert = userService.load(3);
        System.out.println();
        System.out.println(herbert);

        h2con.close();
    }
}
