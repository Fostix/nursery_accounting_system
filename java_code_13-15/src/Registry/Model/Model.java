package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Presenter.IModel;

import java.sql.*;
import java.util.ArrayList;

public class Model implements IModel {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String[] animalTables;
    public Model() {
        animalTables = new String[]{"hamsters", "cats", "dogs", "horses", "donkeys"};
    }

    @Override
    public ArrayList<Animal> getListOfAllPets(String table) {
        connect();
        int id = 0;
        String dateOfBirth = null;
        String type = null;
        String name = null;
        Creator creator = Creator.getInstance();
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(
                    "SELECT p.id, t.type, p.date_of_birth, p.name FROM " + table + " p INNER JOIN animal_type t ON p.id_animal_type = t.id;");
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                type = resultSet.getString("type").trim();
                dateOfBirth = resultSet.getString("date_of_birth").trim();
                name = resultSet.getString("name").trim();
                animals.add(creator.createASpecificAnimal(table, id, dateOfBirth, name));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return animals;
    }

    public void connect() {
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
    public Animal getPetById(String table, int id) {
        connect();
        String dateOfBirth = null;
        String type = null;
        String name = null;
        Creator creator = Creator.getInstance();
        try {
            resultSet = statement.executeQuery( "SELECT id, date_of_birth, name FROM " + table + " WHERE id = " + id + ";");
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                type = resultSet.getString("type").trim();
                dateOfBirth = resultSet.getString("date_of_birth").trim();
                name = resultSet.getString("name").trim();
                return creator.createASpecificAnimal(table, id, dateOfBirth, name);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Animal> getPetByDataOfBirth(String table, String dateOfBirth) {
        connect();
        int id = 0;
        String type = null;
        String name = null;
        Creator creator = Creator.getInstance();
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            for (int i = 0; i < animalTables.length; i++) {
                resultSet = statement.executeQuery( "SELECT id, date_of_birth, name FROM " + animalTables[i] + " WHERE date_of_birth = " + dateOfBirth + ";");
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    dateOfBirth = resultSet.getString("date_of_birth").trim();
                    name = resultSet.getString("name").trim();
                    animals.add(creator.createASpecificAnimal(table, id, dateOfBirth, name));
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public ArrayList<Animal> getPetByName() {

        return null;
    }

    @Override
    public void addNewPet(String table, int type, String dateOfBirth, String name) {
        connect();
        Creator creator = Creator.getInstance();
        try {
            statement.executeUpdate( "INSERT INTO " + table + "(id_animal_type, date_of_birth, name) VALUES (" + type + ", '" + dateOfBirth + "', '" + name + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
