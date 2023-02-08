package Registry.Presenter;

import Registry.Counter.Counter;
import Registry.Model.FriendsOfMan.Animals.Animal;
import Registry.Model.FriendsOfMan.Animals.Exceptions.CountException;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
import Registry.Model.FriendsOfMan.PetCommands.PetCommands;

import java.sql.SQLException;
import java.util.Objects;

public class Presenter {
    private ViewContract viewContract;
    private final IModel model;
    private static final String[] animalType;

    static {
        animalType = new String[]{"hamsters", "cats", "dogs", "horses", "donkeys"}; // "camels" table were delete.
    }

    public Presenter(IModel model) {
        this.model = model;
    }
    public void attachView(ViewContract viewContract) {
        this.viewContract = viewContract;
    }

    public void on() {
        menu();
    }

    public void menu() {
        while (true) {
            viewContract.showMenu();
            viewContract.print("Enter number: ");
            String num = "5";//viewContract.enterData();
            switch (num) {
                case "1" -> {
                    viewContract.println();
                    showAllPets();
                }
                case "2" -> {
                    viewContract.println();
                    showAllPetsInTable();
                }
                case "3" -> {
                    int number = targetPet();
                    viewContract.println();
                    infoPet(number);
                    petManipulation(number);
                }
                case "4" -> {
                    viewContract.println();
                    petManipulation(searchPetById());
                }
                case "5" -> {
                    viewContract.println();
                    addNewPet();
                }
                default -> viewContract.printlnEr("Unexpected value: " + num);
            }
        }
    }

    public void petManipulation(int numberPet) {
        viewContract.petManipulation();
        viewContract.print("Enter number: ");
        String num = viewContract.enterData();
        if (Objects.equals(num, "1"))
            teachANewPetCommand(numberPet);
    }

    public void infoPet(int number) {
        try {
            Animal animal = model.getPetByIdCommand(number);
            printAnimal(animal);
        } catch (SQLException | ClassNotFoundException e) {
            viewContract.println(e.getMessage());
        }
    }

    public void showAllPets() {
        viewContract.println("|   PETS   |");
        for (int i = 0; i < animalType.length; i++) {
            viewContract.println("_".repeat(100));
            viewContract.println(animalType[i]);
            viewContract.println("-".repeat(50));
            try {
                for (Animal animal : model.getListOfAllPets(animalType[i])) {
                    printAnimal(animal);
                }
            } catch (SQLException | ClassNotFoundException e) {
                viewContract.printlnEr(e.getMessage());
            }
            if (i == 2) // after 3 show pack animals
                viewContract.println("|   PACK ANIMALS   |");
        }
    }

    public void showAllPetsInTable() {
        viewContract.print("Enter table name: ");
        String table = viewContract.enterData();
        try {
            for (Animal animal : model.getListOfAllPets(table)) {
                printAnimal(animal);
            }
        } catch (SQLException | ClassNotFoundException e) {
            viewContract.printlnEr(e.getMessage());
        }
    }

    public void printAnimal(Animal animal) {
        viewContract.print(animal.getNumber() + "   ");
        viewContract.print(animal.getId() + "   ");
        viewContract.print(animal.getName() + "   ");
        viewContract.print(animal.getDateOfBirth() + "   ");

        for (int j = 0; j < animal.getListOfCommands().getSize(); j++) {
            viewContract.print(animal.getCommandById(j).toString());
            if (j < animal.getListOfCommands().getSize() - 1)
                viewContract.print(", ");
            else
                viewContract.print(".");
        }
        viewContract.println();
    }

    public int searchPetById() {
        viewContract.print("enter pet table: ");
        String value = viewContract.enterData();
        viewContract.print("enter pet id: ");
        String id = viewContract.enterData();
        Animal animal = null;
        try {
            animal = model.getPetById(value, Integer.parseInt(id));
        } catch (SQLException | ClassNotFoundException e) {
            viewContract.printlnEr(e.getMessage());
        }
        if (animal == null) {
            viewContract.println("wrong");
        } else {
            printAnimal(animal);
        }
        assert animal != null;
        return animal.getNumber();
    }

    public int targetPet() {
        viewContract.print("Enter number pet: ");
        return Integer.parseInt(viewContract.enterData());
    }

    public void teachANewPetCommand(int numberPet) {
        String numberPetString = String.valueOf(numberPet);
        int buttonMenu = 1;
        try {
            PetCommands<Command> commands = model.getPetCommands(numberPetString);
            PetCommands<Command> allCommands = model.getAllCommands();

            viewContract.println();
            viewContract.println("You can teach these/this command(s)");
            for (Object command : allCommands) {
                if (!commands.contain((Command) command)) {
                    viewContract.println(buttonMenu++ + " - " + command.toString());
                }
            }

            for (Object command : commands) {
                allCommands.removeCommand((Command) command);
            }

            if (allCommands.getSize() == 0) {
                viewContract.println("Oh, We will not be able to teach anything new to your pet, he is too smart!");
                viewContract.println();
                return;
            }

            viewContract.println();
            viewContract.print("Which command teach: ");
            int enter = Integer.parseInt(viewContract.enterData());

            int indexCommand = 0;
            Command forFindIndex = allCommands.getById(--enter);

            while (Command.values()[indexCommand] != forFindIndex) {
                indexCommand++;
            }

            viewContract.println();
            model.teachANewPetCommand(numberPetString, ++indexCommand);

        } catch (SQLException | ClassNotFoundException e) {
            viewContract.println(e.getMessage());
        }
    }

    public void addNewPet() {
        viewContract.print("Enter type of pet: ");
        String type = "hamsters";//viewContract.enterData() + "s";
        typePetInId(type);
        viewContract.print("Enter date of birth: ");
        String dateOfBirth = "2012-12-12 15:46:56";//viewContract.enterData();
        viewContract.print("Enter name: ");
        String name = "Vmlyh";//viewContract.enterData();
        Counter counter = new Counter();
        try (counter) {
            counter.add();
            model.addNewPet(type,  model.getLastIdPetKnowsCommand(), typePetInId(type), dateOfBirth, name);
        } catch (SQLException | ClassNotFoundException e) {
            viewContract.printlnEr(e.getMessage());
        }
    }

    public int typePetInId(String type) {
        for (int i = 0; i < animalType.length; i++) {
            if (animalType[i].equals(type)) {
                if (i < 2) {
                    return 1;
                } else return 2;
            }
        }
        return -1;
    }
}
