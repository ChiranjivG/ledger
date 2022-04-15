package in.ledger.repository;

import in.ledger.models.Loan;
import in.ledger.models.Repayment;

public interface IRepository {
    Boolean saveNewLoan(Loan loan);
    Boolean saveNewRepayment(String bank, String borrowerName, Repayment repayment);
    Loan getLoanForPersonFromBank(String borrowerName, String bank);
}
