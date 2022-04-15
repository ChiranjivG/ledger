package com.example.commands;

import com.example.exceptions.InvalidParameterException;
import com.example.handler.CommandHandler;

public class LoanCommand implements Commands{

    private CommandHandler commandHandler;

    public LoanCommand(CommandHandler commandHandler){
        this.commandHandler = commandHandler;
    }

    @Override
    public void execute(String[] args) throws InvalidParameterException {

        if(args.length != 5){
            throw new InvalidParameterException("Incorrect Usage of parameter");
        }
        commandHandler.createLoan(args[0],args[1],args[2],args[3],args[4]);
    }
}
