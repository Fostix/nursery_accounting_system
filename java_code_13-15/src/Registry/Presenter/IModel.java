package Registry.Presenter;

import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;

import java.util.ArrayList;
import java.util.Map;

public interface IModel {
    Map<Animal, ArrayList<Command>> getListOfAllPets(String pet);
    Animal getPetById(String table, int id);
    ArrayList<Animal> getPetByDataOfBirth(String table, String dateOfBirth);
    ArrayList<Animal> getPetByName();
    void addNewPet(String table, int type, String dateOfBirth, String name);
    public ArrayList<Command> showPetCommands(int id);
}
