package Registry.Model.FriendsOfMan.Animals;

import Registry.Model.FriendsOfMan.Animals.Exceptions.IdLessThanOneException;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
import Registry.Model.FriendsOfMan.PetCommands.PetCommands;

public abstract class Animal {
    private int number;
    private int id;
    private String dateOfBirth;
    private String name;
    private PetCommands<Command> commands = new PetCommands<Command>();

    public Animal(int number, int id, String dateOfBirth, String name) {
        this.number = number;
        setId(id);
        this.dateOfBirth = dateOfBirth;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public PetCommands<Command> getListOfCommands() {
        return commands;
    }

    public void addCommand(Command command) {
        this.commands.addCommand(command);
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
                "number=" + number +
                ", id=" + id +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", name='" + name + '\'' +
                ", commands=" + commands +
                '}';
    }
}
