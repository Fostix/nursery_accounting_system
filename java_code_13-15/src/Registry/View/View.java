package Registry.View;

import Registry.Presenter.ViewContract;

public class View implements ViewContract {
    @Override
    public void showMenu() {
        System.out.printf("");
        System.out.println("1 - show list of all pets");
        System.out.println("2 - search pet by id");
        System.out.println("3 - search pet by date of birth");
        System.out.println("4 - search pet by name");
        System.out.println("5 - add new pet");
    }

    @Override
    public void showPets(String data) {

    }

    @Override
    public String enterData() {
        return null;
    }
}
