package Registry.Presenter;

import Registry.Model.FriendsOfMan.Animals.Animal;

import java.util.ArrayList;

public interface IModel {
    ArrayList<Animal> getListOfAllPets(String pet);
    ArrayList<Animal> getPetById();
    ArrayList<Animal> getPetByDataOfBirth();
    ArrayList<Animal> getPetByName();
    ArrayList<Animal> addNewPet();
}
