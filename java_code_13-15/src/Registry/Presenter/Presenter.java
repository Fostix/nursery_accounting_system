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
