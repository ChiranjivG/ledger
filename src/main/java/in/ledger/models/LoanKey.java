package in.ledger.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class LoanKey {
    String borrowerName;
    String bankName;

    public LoanKey(String borrowerName, String bankName) {
        this.borrowerName = borrowerName;
        this.bankName = bankName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanKey)) return false;
        LoanKey key = (LoanKey) o;
        return Objects.equals(borrowerName, key.borrowerName) && Objects.equals(bankName, key.bankName);
    }

    @Override
    public int hashCode(){
        return this.bankName.length()+this.borrowerName.length();
    }

    @Override
    public String toString(){
        return this.borrowerName+" "+this.bankName;
    }
}
