package com.example.repository;

import com.example.models.Loan;
import com.example.models.LoanKey;
import com.example.models.Repayment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoanRepository implements IRepository {
    private Map<LoanKey, Loan> loanBankAndPersonRepo = new HashMap<>();

    public Boolean saveNewLoan(Loan loan){

        if(loan == null){
            return false;
        }

        loanBankAndPersonRepo.put(getLoanRecordKey(loan.getBorrowerName(), loan.getBank()), loan);
        return true;
    }

    public Boolean saveNewRepayment(String bank, String borrowerName, Repayment repayment) {
        LoanKey loanRecordKey = getLoanRecordKey(borrowerName, bank);
        Loan existingLoanDetails = getLoanForPersonFromBank(borrowerName, bank);

        if(existingLoanDetails == null) {
            return false;
        }

        if(existingLoanDetails.getRepaymentList() == null){
            existingLoanDetails.setRepaymentList(new ArrayList<>());
        }

        existingLoanDetails.getRepaymentList().add(repayment);
        return true;
    }

    public Integer getRepoSize(){
        return loanBankAndPersonRepo.size();
    }

    public Loan getLoanForPersonFromBank(String borrowerName, String bank){
        LoanKey key = getLoanRecordKey(borrowerName, bank);
        return loanBankAndPersonRepo.get(key);
    }

    private LoanKey getLoanRecordKey(String borrowerName, String bankName){
        return new LoanKey(borrowerName, bankName);
    }

}
