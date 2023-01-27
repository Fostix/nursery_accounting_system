package PetCommands;

import Animals.Exceptions.AlreadyContainCommand;
import PetCommands.Enums.Command;

import java.util.ArrayList;

public class PetCommands<C extends Command> {
    private ArrayList<C> commands;

    public PetCommands() {
        this.commands = new ArrayList<>();
    }

    public Command getById(int index) {
        return commands.get(index);
    }

    public void addCommand(Command command) {
        if (commands.contains(command))
            throw new AlreadyContainCommand();
        this.commands.add((C) command);
    }

    public int getSize() {
        return commands.size();
    }

    @Override
    public String toString() {
        return "PetCommands.PetCommands{" +
                "commands=" + commands +
                '}';
    }
}
