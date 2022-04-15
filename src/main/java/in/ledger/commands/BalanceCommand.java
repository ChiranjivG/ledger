package in.ledger.commands;

import in.ledger.exceptions.InvalidParameterException;
import in.ledger.handler.CommandHandler;
import in.ledger.service.LoanService;

import java.io.IOException;

public class BalanceCommand implements Commands{

    private CommandHandler commandHandler;

    public BalanceCommand(CommandHandler commandHandler){
        this.commandHandler = commandHandler;
    }

    @Override
    public void execute(String[] args) throws InvalidParameterException {
        if(args.length != 3){
            throw new InvalidParameterException("Incorrect Usage of parameter");
        }
        commandHandler.getBalance(args[0], args[1], args[2]);

    }
}
