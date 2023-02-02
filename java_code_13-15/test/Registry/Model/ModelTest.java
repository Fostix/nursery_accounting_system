package Registry.Model;

import Registry.Model.FriendsOfMan.Animals.Pet.Dog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Model model;

    @BeforeEach
    void setUp() {
        model = new Model();
    }

    @AfterEach
    void tearDown() {
        model = null;
    }

    @Test
    void getListOfAllPets() {
        model = new Model();
        System.out.println(model.getListOfAllPets("dogs"));
        assertEquals(" ", model.getListOfAllPets("dogs"));
    }

    @Test
    void connect() {
    }

    @Test
    void getPetById() {
        model = new Model();
        System.out.println(model.getPetById("dogs", 1));
        assertEquals(new Dog(1, "2012-12-12 15:46:56", "Lucky"), model.getPetById("dogs", 1));
    }

    @Test
    void getPetByDataOfBirth() {
    }

    @Test
    void getPetByName() {
    }

    @Test
    void addNewPet() {
        model.addNewPet("dogs", 1, "2015-10-19 07:01:05", "Bahruma");
        //assertEquals();
    }
}