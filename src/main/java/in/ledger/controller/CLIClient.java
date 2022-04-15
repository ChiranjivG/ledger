package in.ledger.controller;

import in.ledger.factory.CommandFactory;

import java.io.BufferedReader;

public class CLIClient extends Client{
    public CLIClient(
            BufferedReader inputReader,
            CommandFactory commandFactory
    ) {
        super(inputReader, commandFactory);
    }
}
