package in.ledger.controller;

import in.ledger.factory.CommandFactory;
import in.ledger.handler.CommandHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ClientTests {

    private CommandFactory commandFactory;

    @BeforeAll
    public static void setup(){
        CommandHandler handler = CommandHandler.init();
        CommandFactory factory = CommandFactory.init(handler);
    }

    @Test
    public void handleInput_shouldHandleInput() {
        Client client = new CLIClient(
                new BufferedReader(new StringReader("exit")),
                commandFactory
        );
        assertDoesNotThrow(() -> client.handleInput());
    }

}
