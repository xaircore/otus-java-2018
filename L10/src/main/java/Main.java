import com.xairlab.otus.jdbc.entity.User;
import com.xairlab.otus.jdbc.service.UserService;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:h2:mem:";
    private final Connection connection;

    public static void main(String[] args) throws SQLException {
        Main m = new Main();
        m.createTable();

        UserService userService = new UserService(m.getConnection());
        User user1 = new User(7, "James Bond", 25);
        User user2 = new User(1, "Alex Finch", 19);
        User user3 = new User(3, "Steve Mozart", 45);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        m.listTable();

        System.out.println();
        User user4 = new User(3, "Frank Herbert", 60);
        userService.save(user4);
        m.listTable();

        User herbert = userService.load(3, User.class);
        System.out.println();
        System.out.println(herbert);

        m.close();
    }

    public Main() throws SQLException {
        connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);
    }

    private void listTable() throws SQLException {
        System.out.println("Данные в таблице:");
        try (PreparedStatement pst = connection.prepareStatement("select * from User")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        }
    }

    private void createTable() throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("create table User(id long auto_increment, name varchar(50), age int)")) {
            pst.executeUpdate();
        }
    }

    private void close() throws SQLException {
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }
}
