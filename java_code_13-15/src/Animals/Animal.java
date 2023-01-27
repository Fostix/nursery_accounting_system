package Animals;

import Animals.Exceptions.IdLessThanOneException;
import Animals.Exceptions.IdOutOfException;
import PetCommands.Enums.Command;
import PetCommands.PetCommands;

public abstract class Animal {
    private int id;
    private String dateOfBirth;
    private String name;
    private PetCommands<Command> commands;

    public Animal(int id, String dateOfBirth, String name) {
        setId(id);
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.commands = new PetCommands<>();
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
