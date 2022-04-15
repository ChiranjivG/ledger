package com.example.commands;

import com.example.exceptions.InvalidParameterException;
import com.example.handler.CommandHandler;

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
