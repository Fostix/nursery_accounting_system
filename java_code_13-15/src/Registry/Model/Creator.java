package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.Animals.PackAnimal.Donkey;
import Registry.Model.FriendsOfMan.Animals.PackAnimal.Horse;
import Registry.Model.FriendsOfMan.Animals.Pet.Cat;
import Registry.Model.FriendsOfMan.Animals.Pet.Dog;
import Registry.Model.FriendsOfMan.Animals.Pet.Hamster;

public class Creator {

    private static Creator instance = null;

    private Creator() {
    }

    public static Creator getInstance() {
        if (instance == null)
            instance = new Creator();
        return instance;
    }

    public Animal createASpecificAnimal(String kindOfAnimal, int number, int id, String dateOfBirth, String name) {
        return switch (kindOfAnimal) {
            case "cats" -> new Cat(number, id, dateOfBirth, name);
            case "dogs" -> new Dog(number, id, dateOfBirth, name);
            case "hamsters" -> new Hamster(number, id, dateOfBirth, name);
            case "horses" -> new Horse(number, id, dateOfBirth, name);
            case "donkeys" -> new Donkey(number, id, dateOfBirth, name);
            default -> null;
        };
    }
}
