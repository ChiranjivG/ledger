package in.ledger.commands;

import in.ledger.exceptions.InvalidParameterException;

import java.io.IOException;

public interface Commands {
    public void execute(String[] args) throws InvalidParameterException;
}
