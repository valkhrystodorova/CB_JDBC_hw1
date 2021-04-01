package task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/hwtestdb?serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "1995";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Paths.get("src/task1/query.txt")).collect(Collectors.toList());

        registerDriver();

        try (final Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             final Statement statement = connection.createStatement()) {
            lines.forEach(s -> addBatch(statement, s));
//            statement.execute("INSERT into table1 (name, phone) values ('Alla', '22222222')");
//            statement.addBatch("INSERT into table1 (name, phone) values ('Ola', '2222222')");
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void addBatch(Statement statement, String s) {
        try {
            statement.addBatch(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
