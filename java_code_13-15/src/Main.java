import Registry.Model.Model;
import Registry.Presenter.Presenter;
import Registry.View.View;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Presenter presenter = new Presenter(new Model());
        presenter.attachView(new View());
        presenter.on();
    }
}