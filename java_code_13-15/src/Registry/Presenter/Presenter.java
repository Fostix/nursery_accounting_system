package Registry.Presenter;

import Registry.Model.FriendsOfMan.Animals.Animal;

import java.util.ArrayList;

public class Presenter {
    private ViewContract viewContract;
    private final IModel model;
    private ArrayList<Animal> tempPets;

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
        viewContract.showMenu();
        String num = "1"; // viewContract.enterData();
        switch (num) {
            case "1":
                ArrayList<Animal> listAnimal = model.getListOfAllPets("dogs");
                for (Animal animal : listAnimal) {
                    viewContract.print(Integer.toString(animal.getId()));
                    viewContract.print(animal.getDateOfBirth());
                    viewContract.println(animal.getName());
                }
        }
    }

    public void showAllPets() {

    }

    public void searchPetById() {

    }

    public void searchPetByDateOfBirth() {

    }

    public void searchPetByName() {

    }

    public void chooseAPet() {

    }

    public void teachANewPetCommand() {

    }

    public void addNewPet() {

    }
}
