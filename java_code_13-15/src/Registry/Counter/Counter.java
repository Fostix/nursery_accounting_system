package Registry.Counter;

import Registry.Model.FriendsOfMan.Animals.Exceptions.CountException;
import Registry.Model.Model;
import Registry.Model.MySQL.Connector.RequestsMySQL;

import java.sql.SQLException;

public class Counter implements AutoCloseable {
    private int count;
    private boolean flag;

    public Counter()  {
        try {
            this.count = new Model(new RequestsMySQL()).getLastIdPetKnowsCommand();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.flag = true;
    }

    public void add() {
        if (flag)
            count++;
        else
            throw new CountException();
    }

    @Override
    public void close() {
        this.flag = false;
    }

    @Override
    public String toString() {
        return String.format("%d", this.count);
    }
}
