package in.ledger.repository;

import in.ledger.models.Loan;
import in.ledger.models.Repayment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class LoanRepositoryTest {

    private static LoanRepository repoUnderTest;

    @BeforeAll
    private static void setup(){
        repoUnderTest = new LoanRepository();
    }

    @Test
    public void check_empty(){
        assertEquals(0, repoUnderTest.getRepoSize());
    }

    @Test
    public void saveNullLoan_shouldReturnFalse(){
        Loan loan = null;
        Boolean response = repoUnderTest.saveNewLoan(loan);
        assertFalse(response);
        assertEquals(0, repoUnderTest.getRepoSize());
    }

    @Test
    public void saveValidLoan_shouldReturnTrueAndIncreaseRepoSize(){
        Loan loan = new Loan("abc", "def", "Chiranjiv", 1000, 1, BigDecimal.valueOf(2));
        Boolean response = repoUnderTest.saveNewLoan(loan);
        assertTrue(response);
        assertEquals(1, repoUnderTest.getRepoSize());
    }

    @Test
    public void getLoanForPersonFromBank_loanPresent(){
        Loan loan = new Loan("abc", "def", "Chiranjiv", 1000, 1, BigDecimal.valueOf(2));
        Boolean response = repoUnderTest.saveNewLoan(loan);

        Loan loan1 = repoUnderTest.getLoanForPersonFromBank("Chiranjiv", "def");
        assertNotNull(loan1);
    }

    @Test
    public void getLoanForPersonFromBank_loanNotPresent(){
        Loan loan = new Loan("abc", "def", "Chiranjiv", 1000, 1, BigDecimal.valueOf(2));
        Boolean response = repoUnderTest.saveNewLoan(loan);
        Loan loan1 = repoUnderTest.getLoanForPersonFromBank("Chiranjiv", "defg");
        assertNull(loan1);
    }

    @Test
    public void saveNewRepaymentNoExistingLoan_shouldReturnFalse(){
        Repayment repayment = new Repayment(1, BigDecimal.valueOf(100));
        Boolean response = repoUnderTest.saveNewRepayment("def", "Chiranjiv", repayment);
        assertFalse(response);
    }

    @Test
    public void saveNewRepaymentExistingLoan_shouldReturnTrue(){
        Loan loan = new Loan("abc", "def", "Chiranjiv", 1000, 1, BigDecimal.valueOf(2));
        repoUnderTest.saveNewLoan(loan);
        Repayment repayment = new Repayment(1, BigDecimal.valueOf(100));
        Boolean response = repoUnderTest.saveNewRepayment("def", "Chiranjiv", repayment);

        assertTrue(response);
        assertEquals(repoUnderTest.getLoanForPersonFromBank("Chiranjiv", "def").getRepaymentList().size(), 1);
    }
}
