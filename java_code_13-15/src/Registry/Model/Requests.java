package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
import Registry.Model.FriendsOfMan.PetCommands.PetCommands;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Requests {
    ArrayList<Animal> getListOfAllPets(String table) throws SQLException, ClassNotFoundException;
    Animal getPetById(String table, int id) throws SQLException, ClassNotFoundException;
    void addNewPet(String table, int lastPetId, int type, String dateOfBirth, String name) throws SQLException, ClassNotFoundException;
    PetCommands<Command> getPetCommands(String petNumber) throws SQLException, ClassNotFoundException;
    void teachANewPetCommand(String petNumber, int command) throws SQLException, ClassNotFoundException;
    int getLastIdPetKnowsCommand() throws SQLException, ClassNotFoundException;
    Animal getPetByIdCommand(int idCommand) throws SQLException, ClassNotFoundException;
}
