package Registry.Model.FriendsOfMan.Connector;

import java.sql.*;

public class Connect {
    public void connect() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/friends_of_man",
                    "root", "password");
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select id from dogs;");
            int code;
            String title;
            while (resultSet.next()) {
                code = resultSet.getInt("id");
                //title = resultSet.getString("title").trim();
                System.out.println("Code: " + code + " Title:");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
