package Registry.Presenter;

import Registry.Model.FriendsOfMan.Animals.Animal;

import java.util.ArrayList;

public interface IModel {
    ArrayList<Animal> getListOfPets();
    ArrayList<Animal> getPetById();
    ArrayList<Animal> getPetByDataOfBirth();
    ArrayList<Animal> getPetByName();
    ArrayList<Animal> addNewPet();
}
