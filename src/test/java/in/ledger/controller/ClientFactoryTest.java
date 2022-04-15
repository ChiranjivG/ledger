package in.ledger.controller;

import in.ledger.factory.CommandFactory;
import in.ledger.handler.CommandHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientFactoryTest {

    private CommandFactory commandFactory;

    @BeforeAll
    public static void setup(){
        CommandHandler handler = CommandHandler.init();
        CommandFactory factory = CommandFactory.init(handler);
    }

    @Test
    public void buildClientWithNoArg_shouldCreateCLIClient()
            throws FileNotFoundException {
        String[] args = {};
        Client client = ClientFactory.buildClient(args, commandFactory);

        assertTrue(client instanceof CLIClient);
    }

    @Test
    public void buildClientWithValidFilePath_shouldCreateFileClient()
            throws FileNotFoundException {
        String[] args = { "src/main/java/in/ledger/datasource/input1.txt" };
        Client client = ClientFactory.buildClient(args, commandFactory);

        assertTrue(client instanceof FileClient);
    }

    @Test
    public void buildClientWithInvalidFilePath_shouldThrowError()
            throws FileNotFoundException {
        String[] args = { "invalid_file_path.txt" };
        assertThrows(
                FileNotFoundException.class,
                () -> ClientFactory.buildClient(args, commandFactory)
        );
    }

}
