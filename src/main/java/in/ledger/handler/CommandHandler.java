package in.ledger.handler;

import in.ledger.enums.RepositoryType;
import in.ledger.factory.LoanDatastoreFactory;
import in.ledger.models.Loan;
import in.ledger.service.LoanService;

import java.math.BigDecimal;

public class CommandHandler {
    LoanService loanService;

    private CommandHandler(){
    }

    public static CommandHandler init(){
        final CommandHandler commandHandler = new CommandHandler();
        commandHandler.loanService = new LoanService(LoanDatastoreFactory.getRepo(RepositoryType.Loan));

        return commandHandler;
    }
    public void createLoan(String bank, String borrowerName, String principal, String loanTenure, String interestRate){
        loanService.createLoan(bank, borrowerName, Integer.valueOf(principal), Integer.valueOf(loanTenure), BigDecimal.valueOf(Double.parseDouble(interestRate)));
    }

    public void makeRepayment(String bank, String borrowerName, String amount, String emiNumber){
        loanService.makeRepayment(bank, borrowerName, Integer.valueOf(amount), Integer.valueOf(emiNumber));
    }

    public void getBalance(String bank,String borrowerName, String emiNo){

        BigDecimal amountPaid = loanService.totalAmountPaidTillEmi(borrowerName, bank, Integer.parseInt(emiNo));
        Integer emisRemaining = loanService.remainingEmis(borrowerName, bank, Integer.parseInt(emiNo));

        if(amountPaid==null || emisRemaining==null){
            System.out.println(borrowerName+" not found in database");
            return;
        }

        System.out.println(bank+" "+borrowerName+" "+amountPaid.intValue()+" "+emisRemaining);
    }

}
