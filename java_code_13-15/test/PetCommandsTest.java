import Animals.Exceptions.AlreadyContainCommand;
import PetCommands.Enums.Command;
import PetCommands.PetCommands;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetCommandsTest {
    PetCommands<Command> petCommands;
    @BeforeEach
    void setUp() {
        petCommands = new PetCommands<>();
    }

    @AfterEach
    void tearDown() {
        petCommands = null;
    }

    @Test
    void addAndGetTests() {
        petCommands.addCommand(Command.GO);
        assertEquals(Command.GO, petCommands.getById(petCommands.getSize() - 1));
    }

    @Test
    void RepeatCommandTest() {
        petCommands.addCommand(Command.GO);
        boolean flag = false;
        try {
            petCommands.addCommand(Command.GO);
        } catch (AlreadyContainCommand e) {
            flag = true;
        }
        assertEquals(true, flag);
    }
}