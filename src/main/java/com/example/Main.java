package com.example;

import com.example.controller.Client;
import com.example.controller.ClientFactory;
import com.example.factory.CommandFactory;
import com.example.handler.CommandHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
        CommandHandler commandHandler = CommandHandler.init();
        CommandFactory factory = CommandFactory.init(commandHandler);

        try {
            Client client = ClientFactory.buildClient(args, factory);
            client.handleInput();
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry! the supplied input file was not found!");
        } catch (IOException ex) {
            System.out.println("Something went wrong. Please try again!");
        }

    }
}
