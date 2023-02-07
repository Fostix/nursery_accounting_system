package Registry.Presenter;

import Registry.Model.FriendsOfMan.Animals.Animal;

import java.sql.SQLException;
import java.util.ArrayList;

public class Presenter {
    private ViewContract viewContract;
    private final IModel model;
    private ArrayList<Animal> tempPets;
    private static String[] animalType;
    private Animal animal;

    public Presenter(IModel model) {
        this.model = model;
        animalType = new String[]{"hamsters", "cats", "dogs", "horses", "donkeys"}; // "camels" table were delete.
    }
    public void attachView(ViewContract viewContract) {
        this.viewContract = viewContract;
    }

    public void on() {
        menu();
    }

    public void menu() {
        viewContract.showMenu();
        String num = "1"; //viewContract.enterData();
        switch (num) {
            case "1":
                showAllPets();
                break;
            case "2":
                showAllPetsInTable();
                break;
            case "2.5":
                searchPetById();
                teachANewPetCommand();
                break;
            case "3":
                searchPetByDateOfBirth();
                break;
            case "4":
                searchPetByName();
                break;
            case "5":
                addNewPet();
                break;
            case "6":
                teachANewPetCommand();
                break;
        }
    }

    public void showAllPets() {
        viewContract.println("pets");
        for (int i = 0; i < animalType.length; i++) {
            viewContract.println("_".repeat(100));
            viewContract.println(animalType[i]);
            viewContract.println("-".repeat(50));
            try {
                for (Animal a : model.getListOfAllPets(animalType[i])) {
                    viewContract.print(a.getNumber() + "  ");
                    viewContract.print(a.getId() + "  ");
                    viewContract.print(a.getName() + "  ");
                    viewContract.print(a.getDateOfBirth() + "  ");
                    for (int j = 0; j < a.getListOfCommands().getSize(); j++) {
                        viewContract.print(a.getCommandById(j).toString() + ",");
                    }
                    viewContract.println("");
                }
            } catch (SQLException | ClassNotFoundException e) {
                viewContract.printlnEr(e.getMessage());
            }
            if (i == 2) // after 3 show pack animals
                viewContract.println("pack animals");
        }
    }

    public void showAllPetsInTable() {
        viewContract.print("Enter table name:");
        String table = viewContract.enterData();
        try {
            for (Animal a : model.getListOfAllPets(table)) {
                viewContract.print(String.valueOf(a.getId()));
                viewContract.print(a.getName());
                viewContract.print(a.getDateOfBirth());
                for (int j = 0; j < a.getListOfCommands().getSize(); j++) {
                    viewContract.print(a.getCommandById(j).toString() + ",");
                }
                viewContract.println("");
            }
        } catch (SQLException | ClassNotFoundException e) {
            viewContract.printlnEr(e.getMessage());
        }
    }


    public void searchPetById() {
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
            viewContract.print(Integer.toString(animal.getId()));
            viewContract.print(animal.getDateOfBirth());
            viewContract.println(animal.getName());
        }
    }

    public void searchPetByDateOfBirth() {
        viewContract.print("Enter date of birth");
        String data = viewContract.enterData();
        for (String at : animalType) {
            try {
                model.getPetByDataOfBirth(at, data);
            } catch (SQLException | ClassNotFoundException e) {
                viewContract.printlnEr(e.getMessage());
            }
        }
    }

    public void showPetCommands() {
        //model.showPetCommands();
    }

    public void searchPetByName() {

    }

    public void chooseAPet() {

    }

    public void teachANewPetCommand() {
        viewContract.print("Enter number pet: ");
    }

    public void addNewPet() {
        viewContract.print("Enter type of pet:");
        String type = viewContract.enterData() + "s";
        typePetInId(type);
        viewContract.print("Enter date of birth");
        String dateOfBirth = viewContract.enterData();
        viewContract.print("Enter name");
        String data = viewContract.enterData();
        try {
            model.addNewPet(type, typePetInId(type), dateOfBirth, data);
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
