package Registry.Model.FriendsOfMan.PetCommands;

import Registry.Model.FriendsOfMan.Animals.Exceptions.AlreadyContainCommandException;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;

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

    public void addCommand(C command) {
        if (commands.contains(command))
            throw new AlreadyContainCommandException();
        this.commands.add(command);
    }

    public int getSize() {
        return commands.size();
    }

    public void removeCommand(C command) {
        commands.remove(command);
    }

    public boolean contain(C command) {
        return commands.contains(command);
    }

    @Override
    public Iterator iterator() {
        Iterator<Command> iter = new Iterator<>() {
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
