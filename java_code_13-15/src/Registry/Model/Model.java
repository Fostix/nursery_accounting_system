package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Presenter.IModel;

import java.sql.*;
import java.util.ArrayList;

public class Model implements IModel {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    public Model() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/friends_of_man",
                    "root", "password");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Animal> getListOfAllPets(String table) {
        int id = 0;
        String dateOfBirth = null;
        String type = null;
        String name = null;
        Creator creator = Creator.getInstance();
        ArrayList<Animal> a = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(
                    "SELECT p.id, t.type, p.date_of_birth, p.name FROM " + table + " p INNER JOIN animal_type t ON p.id_animal_type = t.id;");
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                type = resultSet.getString("type").trim();
                dateOfBirth = resultSet.getString("date_of_birth").trim();
                name = resultSet.getString("name").trim();
                a.add(creator.createASpecificAnimal(table, id, dateOfBirth, name));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public ArrayList<Animal> getPetById() {
        return null;
    }

    @Override
    public ArrayList<Animal> getPetByDataOfBirth() {
        return null;
    }

    @Override
    public ArrayList<Animal> getPetByName() {
        return null;
    }

    @Override
    public ArrayList<Animal> addNewPet() {
        return null;
    }
}
