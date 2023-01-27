package Animals;

import Animals.Exceptions.IdLessThanOneException;
import Animals.Exceptions.IdOutOfException;
import Animals.Pet.Cat;
import PetCommands.Enums.Command;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Cat(1, "2018-10-07 13:50:04", "Bo");
    }

    @AfterEach
    void tearDown() {
        animal = null;
    }

    @Test
    void setAndGetIdTest() {
        animal.setId(2);
        assertEquals(2, animal.getId());
    }

    @Test
    void setIdExceptionTest1() {
        boolean flag = false;
        try {
            animal.setId(0);
        } catch (IdLessThanOneException e) {
            flag = true;
        }
        assertEquals(true, flag);
    }

    @Test
    void setIdExceptionTest2() {
        boolean flag = false;
        try {
            animal.setId(-1);
        } catch (IdLessThanOneException e) {
            flag = true;
        }
        assertEquals(true, flag);
    }

    @Test
    void getDateOfBirth() {
    }

    @Test
    void setDateOfBirth() {
    }

    @Test
    void setAndGetNameTest() {
        animal.setName("Bahruma");
        assertEquals("Bahruma", animal.getName());
    }

    @Test
    void setAndGetCommandsTests() {
        animal.addCommand(Command.GO);
        assertEquals(Command.GO, animal.getCommandById(0));
    }
}