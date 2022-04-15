package com.example.controller;

import com.example.factory.CommandFactory;

import java.io.BufferedReader;

public class FileClient extends Client{

    public FileClient(
            BufferedReader inputReader,
            CommandFactory commandFactory
    ) {
        super(inputReader, commandFactory);
    }
}
