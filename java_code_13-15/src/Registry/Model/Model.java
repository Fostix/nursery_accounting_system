package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
import Registry.Presenter.IModel;

import java.sql.*;
import java.util.ArrayList;

public class Model implements IModel {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Connection forCommandConnection;
    private Statement forCommandStatement;
    private ResultSet forCommandResultSet;
    private String[] animalTables;
    public Model() {
        animalTables = new String[]{"hamsters", "cats", "dogs", "horses", "donkeys"};
    }

    @Override
    public ArrayList<Animal> getListOfAllPets(String table) {
        connect();
        forCommandConnect();
        ArrayList<Animal> animals = new ArrayList<>();
        int id = 0;
        int idCommand = 0;
        String dateOfBirth;
        String name;
        String command;
        Command enumCom;
        Creator creator = Creator.getInstance();
        Animal animal;
        boolean flag = true;
        try {
            resultSet = statement.executeQuery(
                    "SELECT * \n" +
                            "FROM " + table + " a;");
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                idCommand = resultSet.getInt("id_command");
                dateOfBirth = resultSet.getString("date_of_birth").trim();
                name = resultSet.getString("name").trim();
                animal = creator.createASpecificAnimal(table, id, dateOfBirth, name);
                forCommandResultSet = forCommandStatement.executeQuery(
                        "SELECT co.command\n" +
                                "FROM " + table + " a\n" +
                                "LEFT JOIN pet_knows_commands c ON a.id_command = c.id_pet\n" +
                                "LEFT JOIN pet_commands co ON c.id_commands = co.id\n" +
                                "WHERE a.id_command = " + idCommand + ";"
                );
                while (forCommandResultSet.next()) {
                    command = forCommandResultSet.getString("command");
                    if (command != null) {
                        enumCom = Command.valueOf(command);
                        animal.addCommand(enumCom);
                    }
                }
                animals.add(animal);
            }
            resultSet.close();
            statement.close();
            connection.close();
            forCommandResultSet.close();
            forCommandStatement.close();
            forCommandConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void forCommandConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            forCommandConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/friends_of_man",
                    "root", "password");
            forCommandStatement = forCommandConnection.createStatement();
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
            e.printStackTrace();
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
                resultSet = statement.executeQuery( "SELECT id, date_of_birth, name FROM " + animalTables[i] + " WHERE date_of_birth = '" + dateOfBirth + "';");
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

    public ArrayList<Command> showPetCommands(int id) {
//        connect();
//        String type = null;
//        String name = null;
//        Creator creator = Creator.getInstance();
//        ArrayList<Animal> animals = new ArrayList<>();
//        try {
//            for (int i = 0; i < animalTables.length; i++) {
//                resultSet = statement.executeQuery( "SELECT c.id_commands FROM " + animalTables[i] + " WHERE date_of_birth = '" + dateOfBirth + "';");
//                while (resultSet.next()) {
//                    id = resultSet.getInt("id");
//                    dateOfBirth = resultSet.getString("date_of_birth").trim();
//                    name = resultSet.getString("name").trim();
//                    animals.add(creator.createASpecificAnimal(table, id, dateOfBirth, name));
//                }
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        SELECT c.id_commands
//        FROM dogs a
//        INNER JOIN pet_knows_commands c ON a.id = c.id_pet
//        WHERE a.id = 1;
        return null;
    }
}
