package in.ledger.factory;

import in.ledger.Constants;
import in.ledger.commands.BalanceCommand;
import in.ledger.commands.Commands;
import in.ledger.commands.LoanCommand;
import in.ledger.commands.PaymentCommand;
import in.ledger.exceptions.CommandNotFoundException;
import in.ledger.exceptions.InvalidParameterException;
import in.ledger.handler.CommandHandler;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<String, Commands> commands;

    private CommandFactory() {
        commands = new HashMap<>();
    }

    public static CommandFactory init(CommandHandler commandHandler){
        final CommandFactory cf = new CommandFactory();

        cf.addCommand(Constants.LOAN_COMMAND, new LoanCommand(commandHandler));
        cf.addCommand(Constants.REPAYMENT_COMMAND, new PaymentCommand(commandHandler));
        cf.addCommand(Constants.BALANCE_COMMAND, new BalanceCommand(commandHandler));

        return cf;
    }

    public void executeCommand(String name, String[] params)
            throws InvalidParameterException, CommandNotFoundException {
        if (commands.containsKey(name)) {
            commands.get(name).execute(params);
        } else {
            throw new CommandNotFoundException(name);
        }
    }

    public void addCommand(String name, Commands command) {
        commands.put(name, command);
    }
}
