package Registry.Model.MySQL.Connector;

import Registry.Model.Creator;
import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
import Registry.Model.FriendsOfMan.PetCommands.PetCommands;
import Registry.Model.Requests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Registry.Model.MySQL.Connector.Connect.closeAll;

public class RequestsMySQL implements Requests {
    @Override
    public ArrayList<Animal> getListOfAllPets(String table) throws SQLException, ClassNotFoundException {
        Connection connect = Connect.connect();
        Statement statement = Connect.connectStatement(connect);
        Connection forCommandConnection = Connect.connect();
        Statement forCommandStatement = Connect.connectStatement(connect);

        ArrayList<Animal> animals = new ArrayList<>();
        int id = 0;
        int idCommand = 0;
        String dateOfBirth;
        String name;
        String command;
        Command enumCom;
        Creator creator = Creator.getInstance();
        Animal animal;
        ResultSet forCommandResultSet = null;

        ResultSet resultSet = statement.executeQuery(
                "SELECT * \n" +
                        "FROM " + table + " a;");
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            idCommand = resultSet.getInt("id_command");
            dateOfBirth = resultSet.getString("date_of_birth").trim();
            name = resultSet.getString("name").trim();
            animal = creator.createASpecificAnimal(table, idCommand, id, dateOfBirth, name);
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
        closeAll(connect, statement, resultSet);
        closeAll(forCommandConnection, forCommandStatement, forCommandResultSet);
        return animals;
    }

    @Override
    public Animal getPetById(String table, int id) throws SQLException, ClassNotFoundException {
        Connection connect = Connect.connect();
        Statement statement = Connect.connectStatement(connect);

        int idCommand = 0;
        String dateOfBirth = null;
        String name = null;
        Creator creator = Creator.getInstance();
        ResultSet resultSet = statement.executeQuery( "SELECT id, id_command, date_of_birth, name FROM " + table + " WHERE id = " + id + ";");
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            idCommand = resultSet.getInt("id_command");
            dateOfBirth = resultSet.getString("date_of_birth").trim();
            name = resultSet.getString("name").trim();
        }
        Connect.closeAll(connect, statement, resultSet);

        return creator.createASpecificAnimal(table, idCommand, id, dateOfBirth, name);
    }

    @Override
    public void addNewPet(String table, int lastPetId, int type, String dateOfBirth, String name) throws SQLException, ClassNotFoundException {
        Connection connect = Connect.connect();
        Statement statement = Connect.connectStatement(connect);
        statement.executeUpdate( "INSERT INTO " + table + "(id_command, id_animal_type, date_of_birth, name) VALUE ('"
                + ++lastPetId + "', " + type + ", '" + dateOfBirth + "', '" + name + "');");
        Connect.closeAll(connect, statement);
    }

    @Override
    public PetCommands<Command> getPetCommands(String petNumber) throws SQLException, ClassNotFoundException {
        Command enumCom;
        String getCommand;
        PetCommands<Command> commands = new PetCommands<>();
        Connection connect = Connect.connect();
        Statement statement = Connect.connectStatement(connect);

        ResultSet resultSet = statement.executeQuery("SELECT c.command\n" +
                "FROM pet_knows_commands k\n" +
                "INNER JOIN pet_commands c \n" +
                "ON c.id = k.id_commands\n" +
                "WHERE id_pet = " + petNumber + ";");
        while (resultSet.next()) {
            getCommand = resultSet.getString("command");
            enumCom = Command.valueOf(getCommand);
            commands.addCommand(enumCom);
        }
        Connect.closeAll(connect, statement, resultSet);
        return commands;
    }

    @Override
    public void teachANewPetCommand(String petNumber, int command) throws SQLException, ClassNotFoundException {
        Connection connect = Connect.connect();
        Statement statement = Connect.connectStatement(connect);

        statement.executeUpdate(
                "INSERT INTO pet_knows_commands (id_pet, id_commands)\n" +
                        "VALUE\n" +
                        "(" + petNumber + ", " + command + ");"
        );
        closeAll(connect, statement);
        // query witch command don't know.
        // return commands which pet don't know.
    }

    @Override
    public int getLastIdPetKnowsCommand() throws SQLException, ClassNotFoundException {
        int lastValue = 0;
        Connection connect = Connect.connect();
        Statement statement = Connect.connectStatement(connect);

        statement.executeUpdate( "CREATE temporary TABLE max_value_all_pets AS\n" +
                "SELECT MAX(id_command) AS id_command\n" +
                "FROM hamsters\n" +
                "UNION\n" +
                "SELECT MAX(id_command) AS id_command\n" +
                "FROM dogs\n" +
                "UNION\n" +
                "SELECT MAX(id_command) AS id_command\n" +
                "FROM cats\n" +
                "UNION\n" +
                "SELECT MAX(id_command) AS id_command\n" +
                "FROM donkeys\n" +
                "UNION\n" +
                "SELECT MAX(id_command) AS id_command\n" +
                "FROM horses;");
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id_command) AS id_command " +
                "FROM max_value_all_pets;");
        if (resultSet.next()) {
            lastValue = resultSet.getInt("id_command");
        }
        statement.executeUpdate("DROP TABLE max_value_all_pets;");

        Connect.closeAll(connect, statement, resultSet);
        return lastValue;
    }

    @Override
    public Animal getPetByIdCommand(int idCommand) throws SQLException, ClassNotFoundException {
        Connection connect = Connect.connect();
        Statement statement = Connect.connectStatement(connect);

        Connection forCommandConnection = Connect.connect();
        Statement forCommandStatement = Connect.connectStatement(connect);

        int id = 0;
        String dateOfBirth;
        String name;
        String command;
        Command enumCom;
        Creator creator = Creator.getInstance();
        Animal animal = null;
        ResultSet forCommandResultSet = null;

        statement.executeUpdate( "CREATE TABLE all_pets AS\n" +
                "SELECT *\n" +
                "FROM hamsters\n" +
                "UNION\n" +
                "SELECT *\n" +
                "FROM dogs\n" +
                "UNION\n" +
                "SELECT *\n" +
                "FROM cats\n" +
                "UNION\n" +
                "SELECT *\n" +
                "FROM donkeys\n" +
                "UNION\n" +
                "SELECT *\n" +
                "FROM horses;"
        );

        ResultSet resultSet = statement.executeQuery("SELECT *\n" +
                "FROM all_pets a\n" +
                "WHERE id_command = " + idCommand + ";");

        while (resultSet.next()) {
            id = resultSet.getInt("id");
            idCommand = resultSet.getInt("id_command");
            dateOfBirth = resultSet.getString("date_of_birth").trim();
            name = resultSet.getString("name").trim();
            animal = creator.createASpecificAnimal("cats", idCommand, id, dateOfBirth, name);

            forCommandResultSet = forCommandStatement.executeQuery(
                    "SELECT co.command\n" +
                            "FROM all_pets a\n" +
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
        }
        statement.executeUpdate("DROP TABLE all_pets;");

        closeAll(connect, statement, resultSet);
        assert forCommandResultSet != null;
        closeAll(forCommandConnection, forCommandStatement, forCommandResultSet);
        return animal;
    }
}