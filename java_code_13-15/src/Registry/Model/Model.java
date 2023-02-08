package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
import Registry.Model.FriendsOfMan.PetCommands.PetCommands;
import Registry.Presenter.IModel;

import java.sql.*;
import java.util.ArrayList;

public class Model implements IModel {
    private final Requests requests;

    public Model(Requests requests) {
        this.requests = requests;
    }

    @Override
    public ArrayList<Animal> getListOfAllPets(String table) throws SQLException, ClassNotFoundException {
        return requests.getListOfAllPets(table);
    }

    @Override
    public Animal getPetById(String table, int id) throws SQLException, ClassNotFoundException {
        return requests.getPetById(table, id);
    }

    @Override
    public void addNewPet(String table, int lastPetId, int type, String dateOfBirth, String name) throws SQLException, ClassNotFoundException {
        requests.addNewPet(table, lastPetId, type, dateOfBirth, name);
    }

    @Override
    public PetCommands<Command> getPetCommands(String petNumber) throws SQLException, ClassNotFoundException {
        return requests.getPetCommands(petNumber);
    }

    @Override
    public PetCommands<Command> getAllCommands() {
        PetCommands<Command> commands = new PetCommands<>();
        commands.addCommand(Command.GO);
        commands.addCommand(Command.STOP);
        commands.addCommand(Command.RUN_AWAY);
        commands.addCommand(Command.COME_UP);
        commands.addCommand(Command.LIE);
        commands.addCommand(Command.JUMP);
        commands.addCommand(Command.TRUP);
        commands.addCommand(Command.STAND_UP);
        return commands;
    }

    @Override
    public void teachANewPetCommand(String petNumber, int command) throws SQLException, ClassNotFoundException {
        requests.teachANewPetCommand(petNumber, command);
    }

    public int getLastIdPetKnowsCommand() throws SQLException, ClassNotFoundException {
        return requests.getLastIdPetKnowsCommand();
    }

    @Override
    public Animal getPetByIdCommand(int idCommand) throws SQLException, ClassNotFoundException {
        return requests.getPetByIdCommand(idCommand);
    }
}
