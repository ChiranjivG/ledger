package in.ledger.commands;

import in.ledger.exceptions.InvalidParameterException;
import in.ledger.handler.CommandHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BalanceCommandTest {

    private static CommandHandler commandHandler;
    private static BalanceCommand command;

    @BeforeAll
    public static void setup() {
        commandHandler = CommandHandler.init();
        command = new BalanceCommand(commandHandler);
    }

    @Test
    public void executeCommand_invalidArgs() {
        String[] args = {"a"};
        assertThrows(InvalidParameterException.class, ()->{command.execute(args);});
    }

    @Test
    public void executeCommand_validArgs() {
        String[] args = {"MBI", "Harry", "12"};
        assertDoesNotThrow(()->{command.execute(args);});
    }


}
