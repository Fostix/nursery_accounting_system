package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Animal;

import java.util.ArrayList;

public class Pets<A extends Animal> {
    private ArrayList<Animal> pets;

    public ArrayList<Animal> getListOfAllPets() {
        return pets;
    }

    public ArrayList<Animal> getPetById(int id) {
        return pets;
    }

    public ArrayList<Animal> getPetByDateOfBirth(String data) {
        return pets;
    }

    public ArrayList<Animal> getPetByName(String name) {
        return pets;
    }

    public void addNewPet() {

    }
}
