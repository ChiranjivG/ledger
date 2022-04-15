package com.example.commands;

import com.example.exceptions.InvalidParameterException;
import com.example.handler.CommandHandler;

public class PaymentCommand implements Commands{

    private CommandHandler commandHandler;

    public PaymentCommand(CommandHandler commandHandler){
        this.commandHandler = commandHandler;
    }

    @Override
    public void execute(String[] args) throws InvalidParameterException {
        if(args.length != 4){
            throw new InvalidParameterException("Incorrect Usage of parameter");
        }
        commandHandler.makeRepayment(args[0],args[1],args[2],args[3]);
    }
}
