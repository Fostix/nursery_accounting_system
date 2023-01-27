package Animals;

import PetCommands.Enums.Command;
import PetCommands.PetCommands;

public abstract class Animal {
    private int id;
    // private DateTime dateOfBirth;
    private String name;
    private PetCommands<Command> commands;

    public Animal(int id, String name) {
        this.id = id;
        this.name = name;
        this.commands = new PetCommands<>();
    }
}
