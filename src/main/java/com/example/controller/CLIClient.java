package com.example.controller;

import com.example.factory.CommandFactory;

import java.io.BufferedReader;

public class CLIClient extends Client{
    public CLIClient(
            BufferedReader inputReader,
            CommandFactory commandFactory
    ) {
        super(inputReader, commandFactory);
    }
}
