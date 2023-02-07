package Registry.Presenter;

import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
import Registry.Model.FriendsOfMan.PetCommands.PetCommands;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IModel {
    ArrayList<Animal> getListOfAllPets(String pet) throws SQLException, ClassNotFoundException;
    Animal getPetById(String table, int id) throws SQLException, ClassNotFoundException;
    ArrayList<Animal> getPetByDataOfBirth(String table, String dateOfBirth) throws SQLException, ClassNotFoundException;
    ArrayList<Animal> getPetByName();
    void addNewPet(String table, int type, String dateOfBirth, String name) throws SQLException, ClassNotFoundException;
    void teachANewPetCommand(String petNumber) throws SQLException, ClassNotFoundException;
    PetCommands<Command> getPetCommands(String petNumber) throws SQLException, ClassNotFoundException ;
    ArrayList<Command> showPetCommands(int id);
}
