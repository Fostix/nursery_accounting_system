package Registry.Model.FriendsOfMan.Animals;

import Registry.Model.FriendsOfMan.Animals.Exceptions.IdLessThanOneException;
import Registry.Model.FriendsOfMan.Animals.Exceptions.IdOutOfException;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
import Registry.Model.FriendsOfMan.PetCommands.PetCommands;

public abstract class Animal {
    private int id;
    private String dateOfBirth;
    private String name;
    private PetCommands<Command> commands = new PetCommands<Command>();

    public Animal(int id, String dateOfBirth, String name) {
        setId(id);
        this.dateOfBirth = dateOfBirth;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > Integer.MAX_VALUE)
            throw new IdOutOfException();
        if (id < 1)
            throw new IdLessThanOneException();
        this.id = id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Command getCommandById(int id) {
        return commands.getById(id);
    }

    public void addCommand(Command commands) {
        this.commands.addCommand(commands);
    }

    public void removeCommands(Command command) {
        commands.removeCommand(command);
    }

    public int getCountCommands() {
        return commands.getSize();
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", name='" + name + '\'' +
                ", commands=" + commands +
                '}';
    }
}
