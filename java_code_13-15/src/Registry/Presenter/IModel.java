package Registry.Presenter;

import Registry.Model.FriendsOfMan.Animals.Animal;

import java.util.ArrayList;

public interface IModel {
    ArrayList<Animal> getListOfAllPets(String pet);
    Animal getPetById(String table, int id);
    ArrayList<Animal> getPetByDataOfBirth(String table, String dateOfBirth);
    ArrayList<Animal> getPetByName();
    void addNewPet(String table, int type, String dateOfBirth, String name);
}
