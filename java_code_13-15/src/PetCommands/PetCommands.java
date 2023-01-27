package PetCommands;

import Animals.Exceptions.AlreadyContainCommand;
import PetCommands.Enums.Command;

import java.util.ArrayList;
import java.util.Iterator;

public class PetCommands<C extends Command> implements Iterable {
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

    public void removeCommand(Command command) {
        commands.remove(command);
    }

    @Override
    public Iterator iterator() {
        Iterator<Command> iter = new Iterator<Command>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < commands.size();
            }

            @Override
            public Command next() {
                return commands.get(index++);
            }
        };
        return iter;
    }

    @Override
    public String toString() {
        return "PetCommands.PetCommands{" +
                "commands=" + commands +
                '}';
    }
}
