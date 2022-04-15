package com.example.commands;

import com.example.exceptions.InvalidParameterException;

public interface Commands {
    public void execute(String[] args) throws InvalidParameterException;
}
