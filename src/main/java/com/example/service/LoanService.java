package com.example.service;

import com.example.models.Loan;
import com.example.models.Repayment;
import com.example.repository.IRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class LoanService {

    IRepository loanRepository;

    public LoanService(IRepository loanRepository){
        this.loanRepository = loanRepository;
    }

    public Boolean createLoan(String bankName, String borrowerName, Integer principal, Integer loanTenure, BigDecimal interest){

        Loan loan = new Loan(UUID.randomUUID().toString(), bankName, borrowerName, principal, loanTenure, interest);
        return loanRepository.saveNewLoan(loan);
    }

    public Loan getLoanDetails(String borrowerName, String bank){
        return loanRepository.getLoanForPersonFromBank(borrowerName, bank);
    }

    public BigDecimal totalAmountPaidTillEmi(String borrowerName, String bank, int emi){

        Loan loan = loanRepository.getLoanForPersonFromBank(borrowerName, bank);
        if(loan == null){
            return null;
        }

        BigDecimal emiAmount = loan.emiAmount();
        BigDecimal totalAmountPaidByEmi = emiAmount.multiply(BigDecimal.valueOf(emi));
        Integer lumpSumAmountPaid = lumpSumAmountPaidTillEmiNumber(borrowerName, bank, emi);

        return totalAmountPaidByEmi.add(BigDecimal.valueOf(lumpSumAmountPaid));
    }

    public Integer remainingEmis(String borrowerName, String bank, int emi){
        Loan loan = loanRepository.getLoanForPersonFromBank(borrowerName, bank);
        if(loan == null){
            return null;
        }

        BigDecimal totalAmountPaidTillEmi = totalAmountPaidTillEmi(borrowerName, bank, emi);
        BigDecimal amountToBePaid = loan.totalAmountToBePaid().subtract(totalAmountPaidTillEmi);

        double remainingEmis = Math.ceil(amountToBePaid.divide(loan.emiAmount(), 2, RoundingMode.HALF_UP).doubleValue());

        return (int)remainingEmis;
    }

    public Integer lumpSumAmountPaidTillEmiNumber(String borrowerName, String bank, Integer emiNumber){
        Loan loan = loanRepository.getLoanForPersonFromBank(borrowerName, bank);

        if(loan == null)
            return 0;

        if(loan.getRepaymentList() == null || loan.getRepaymentList().size() == 0){
            return 0;
        }

        return loan.getRepaymentList().stream()
                .filter(repayment -> repayment.getEmiNumber() <= emiNumber)
                .map(Repayment::getAmount)
                .mapToInt(BigDecimal::intValue)
                .sum();
    }

    public Boolean makeRepayment(String bank, String borrowerName, Integer amount, Integer emiNo){
        Repayment repayment = new Repayment(emiNo, BigDecimal.valueOf(amount));
        return loanRepository.saveNewRepayment(bank, borrowerName, repayment);
    }
}
