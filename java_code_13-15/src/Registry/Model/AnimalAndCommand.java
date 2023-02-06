package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
import Registry.Model.FriendsOfMan.PetCommands.PetCommands;

import java.util.ArrayList;

public class AnimalAndCommand {
    private Animal animal;
    private ArrayList<Command> commands;

    public AnimalAndCommand(Animal animal, ArrayList<Command> commands) {
        this.animal = animal;
        this.commands = commands;
    }

    public AnimalAndCommand(Animal animal) {
        this.animal = animal;
    }

    public AnimalAndCommand() {
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "AnimalAndCommand{" +
                "animal=" + animal +
                ", commands=" + commands +
                '}';
    }
}
