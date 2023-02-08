package Registry.Model.MySQL.Connector;

import java.sql.*;

public class Connect {
    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/friends_of_man",
                "root", "password");
    }

    public static Statement connectStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

    public static void closeAll(Connection connection, Statement statement) throws SQLException {
        statement.close();
        connection.close();
    }
}
