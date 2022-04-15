package com.example.repository;

import com.example.models.Loan;
import com.example.models.Repayment;

public interface IRepository {
    Boolean saveNewLoan(Loan loan);
    Boolean saveNewRepayment(String bank, String borrowerName, Repayment repayment);
    Loan getLoanForPersonFromBank(String borrowerName, String bank);
}
