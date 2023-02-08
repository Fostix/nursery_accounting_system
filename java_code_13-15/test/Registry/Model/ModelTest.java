package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Pet.Dog;
import Registry.Model.MySQL.Connector.RequestsMySQL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Model model;

    @BeforeEach
    void setUp() {
        model = new Model(new RequestsMySQL());
    }

    @AfterEach
    void tearDown() {
        model = null;
    }

    @Test
    void getListOfAllPets() {
        try {
            System.out.println(model.getListOfAllPets("dogs"));
            assertEquals(" ", model.getListOfAllPets("dogs"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void connect() {
    }

    @Test
    void getPetById() {
        try {
            System.out.println(model.getPetById("dogs", 1));
            assertEquals(new Dog(1, 1, "2012-12-12 15:46:56", "Lucky"), model.getPetById("dogs", 1));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getPetByDataOfBirth() {
    }

    @Test
    void getPetByName() {
    }

    @Test
    void addNewPet() {
        try {
            model.addNewPet("dogs", 1,1, "2015-10-19 07:01:05", "Bahruma");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //assertEquals();
    }
}