package in.ledger.controller;

import in.ledger.factory.CommandFactory;

import java.io.BufferedReader;

public class FileClient extends Client{

    public FileClient(
            BufferedReader inputReader,
            CommandFactory commandFactory
    ) {
        super(inputReader, commandFactory);
    }
}
