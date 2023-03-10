package Registry.Model.FriendsOfMan.Animals;

import Registry.Model.FriendsOfMan.Animals.Exceptions.AlreadyContainCommandException;
import Registry.Model.FriendsOfMan.Animals.Exceptions.IdLessThanOneException;
import Registry.Model.FriendsOfMan.Animals.Pet.Cat;
import Registry.Model.FriendsOfMan.PetCommands.Enums.Command;
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
    void setAndGetDateOfBirth() {
        animal.setDateOfBirth("2021-06-11 03:18:43");
        assertEquals("2021-06-11 03:18:43", animal.getDateOfBirth());
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

    @Test
    void setAndGetSeveralCommandsTests() {
        animal.addCommand(Command.GO);
        animal.addCommand(Command.JUMP);
        animal.addCommand(Command.LIE);
        animal.addCommand(Command.STAND_UP);
        animal.addCommand(Command.RUN_AWAY);
        animal.addCommand(Command.STOP);
        animal.addCommand(Command.TRUP);
        animal.addCommand(Command.COME_UP);
        assertEquals(Command.GO, animal.getCommandById(0));
        assertEquals(Command.JUMP, animal.getCommandById(1));
        assertEquals(Command.LIE, animal.getCommandById(2));
        assertEquals(Command.STAND_UP, animal.getCommandById(3));
        assertEquals(Command.RUN_AWAY, animal.getCommandById(4));
        assertEquals(Command.STOP, animal.getCommandById(5));
        assertEquals(Command.TRUP, animal.getCommandById(6));
        assertEquals(Command.COME_UP, animal.getCommandById(7));
    }

    //TODO if delete, double
    @Test
    void removeCommandsTests() {
        animal.addCommand(Command.GO);
        animal.removeCommands(Command.GO);
        animal.addCommand(Command.JUMP);
        assertEquals(Command.JUMP, animal.getCommandById(0));
        boolean flag = false;
        try {
            assertEquals(Command.JUMP, animal.getCommandById(1));
        } catch (IndexOutOfBoundsException e) {
            flag = true;
        }
        assertEquals(true, flag);
    }

    @Test
    void addTheSameCommandTest() {
        animal.addCommand(Command.GO);
        boolean flag = false;
        try {
            animal.addCommand(Command.GO);
        } catch (AlreadyContainCommandException e) {
            flag = true;
        }
        assertEquals(true, flag);
    }

    @Test
    void getCountCommandTest() {
        animal.addCommand(Command.GO);
        animal.addCommand(Command.JUMP);
        animal.addCommand(Command.LIE);
        animal.addCommand(Command.STAND_UP);
        animal.addCommand(Command.RUN_AWAY);
        animal.addCommand(Command.STOP);
        animal.addCommand(Command.TRUP);
        animal.addCommand(Command.COME_UP);
        assertEquals(8, animal.getCountCommands());
    }
}