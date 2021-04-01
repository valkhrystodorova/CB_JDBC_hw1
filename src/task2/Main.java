package task2;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/myjoinsdb?serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "1995";
    private static final String GET_CONTACTS = "SELECT e.name, e.phone, p_info.city from employees e inner join personal_info p_info on e.emp_id = p_info.emp_id";
    private static final String GET_SINGLE = "select e.name, e.phone, pi.birth_date from employees e " +
            "inner join personal_info pi on e.emp_id = pi.emp_id where family_status = 'single'";
    private static final String GET_MANAGERS = "SELECT e.name, e.phone, pi.birth_date from employees e inner join personal_info pi on e.emp_id = pi.emp_id " +
            "inner join positions on e.emp_id = positions.emp_id where position = 'manager'";

    public static void main(String[] args) {
        registerDriver();

        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {

            getCotacts(connection);
            System.out.println();
            getSingle(connection);
            System.out.println();
            getManagers(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void getCotacts(Connection connection) throws SQLException {
        try (PreparedStatement statement1 = connection.prepareStatement(GET_CONTACTS)) {
            ResultSet result1 = statement1.executeQuery();
            while (result1.next()) {
                String name = result1.getString(1);
                String phone = result1.getString(2);
                String city = result1.getString(3);
                System.out.printf("%6s, %6s, %6s%n", name, phone, city);
            }
        }catch (SQLException e){
            System.out.println("GET_CONTACTS error");
        }

    }

    private static void getSingle(Connection connection) throws SQLException {
            try (PreparedStatement statement2 = connection.prepareStatement(GET_SINGLE)) {

                ResultSet result = statement2.executeQuery();
                while (result.next()) {
                    String name = result.getString(1);
                    String phone = result.getString(2);
                    Date birth_date = result.getDate(3);
                    System.out.printf("%6s, %6s, %6s%n", name, phone, birth_date);

                }
            }
            catch (SQLException e){
                System.out.println("GET_SINGLE error");
            }
        }
    private static void getManagers (Connection connection) throws SQLException {
        try (PreparedStatement statement3 = connection.prepareStatement(GET_MANAGERS)) {
            ResultSet result = statement3.executeQuery();
            while (result.next()) {
                String name = result.getString(1);
                String phone = result.getString(2);
                Date birth_date = result.getDate(3);
                System.out.printf("%6s, %6s, %6s%n", name, phone, birth_date);
            }
        }catch (SQLException e){
            System.out.println("GET_MANAGERS error");
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
