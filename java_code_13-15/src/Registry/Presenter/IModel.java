package Registry.Presenter;

import Registry.Model.AnimalAndCommand;
import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface IModel {
    ArrayList<Animal> getListOfAllPets(String pet) throws SQLException, ClassNotFoundException;
    Animal getPetById(String table, int id) throws SQLException, ClassNotFoundException;
    ArrayList<Animal> getPetByDataOfBirth(String table, String dateOfBirth) throws SQLException, ClassNotFoundException;
    ArrayList<Animal> getPetByName();
    void addNewPet(String table, int type, String dateOfBirth, String name) throws SQLException, ClassNotFoundException;
    public ArrayList<Command> showPetCommands(int id);
}
