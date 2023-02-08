package Registry.Model.FriendsOfMan.Animals.Pet;


import Registry.Model.FriendsOfMan.Animals.Animal;

public abstract class Pet extends Animal {
    Pet(int id, int number, String dateOfBirth, String name) {
        super(id, number, dateOfBirth, name);
    }
}
