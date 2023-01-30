package Registry.View;

import Registry.Presenter.ViewContract;

import java.util.Scanner;

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
    public void print(String data) {
        System.out.print(data + " ");
    }

    @Override
    public void println(String data) {
        System.out.println(data);
    }

    @Override
    public String enterData() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
