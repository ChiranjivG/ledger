package in.ledger.commands;

import in.ledger.exceptions.InvalidParameterException;
import in.ledger.handler.CommandHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;

public class LoanCommandTest {

    private static CommandHandler commandHandler;
    private static LoanCommand command;

    @BeforeAll
    public static void setup() {
        commandHandler = CommandHandler.init();
        command = new LoanCommand(commandHandler);
    }

    @Test
    public void executeCommand_invalidArgs() {
        String[] args = {"a"};
        assertThrows(InvalidParameterException.class, ()->{command.execute(args);});
    }

    @Test
    public void executeCommand_validArgs() {
        String[] args = {"IDIDI", "Dale", "10000", "5", "4"};
        assertDoesNotThrow(()->{command.execute(args);});
    }
}
