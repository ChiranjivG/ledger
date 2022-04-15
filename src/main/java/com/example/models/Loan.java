package com.example.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Loan {
    String loanId;
    String bank;
    String borrowerName;
    Integer principle;
    Integer loanTenure;
    BigDecimal interest;
    List<Repayment> repaymentList;

    public BigDecimal emiAmount() {

        BigDecimal amount = totalAmountToBePaid();
        if(amount.doubleValue() >0){
            return BigDecimal.valueOf(Math.ceil(amount.divide(BigDecimal.valueOf(this.loanTenure*12), 2, RoundingMode.HALF_UP).doubleValue()));
        }
        else return BigDecimal.ZERO;
    }
    public BigDecimal totalAmountToBePaid() {
        if(loanTenure>0){
            return  ((BigDecimal.valueOf(this.principle)
                    .multiply(BigDecimal.valueOf(this.loanTenure))
                    .multiply(this.interest)
                    .divide(BigDecimal.valueOf(100)))).add(BigDecimal.valueOf(this.principle));
        }
        return BigDecimal.ZERO;
    }
    public Loan(String loanId, String bank, String borrowerName, Integer principle, Integer loanTenure, BigDecimal interest) {
        this.loanId = loanId;
        this.bank = bank;
        this.borrowerName = borrowerName;
        this.principle = principle;
        this.loanTenure = loanTenure;
        this.interest = interest;
        this.repaymentList = new ArrayList<>();
    }

    public Loan() {

    }

}
