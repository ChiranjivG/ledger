package com.example.service;

import com.example.enums.RepositoryType;
import com.example.factory.LoanDatastoreFactory;
import com.example.repository.IRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {
    private static IRepository loanRepository;


    @BeforeAll
    public static void setup() {
        loanRepository = LoanDatastoreFactory.getRepo(RepositoryType.Loan);
    }

    @Test
    public void createLoan_shouldReturnTrue(){
        LoanService service = new LoanService(loanRepository);
        Boolean response = service.createLoan( "def", "Chiranjiv", 1000, 1, BigDecimal.valueOf(2));

        assertTrue(response);
    }

    @Test
    public void totalAmountPaidTillEmiLoanDoesNotExist_ShouldReturnNull(){
        LoanService service = new LoanService(loanRepository);
        BigDecimal response = service.totalAmountPaidTillEmi("Chiranjiv", "def", 1);
        assertNull(response);
    }

    @Test
    public void totalAmountPaidTillEmiLoanExist_ShouldReturnNonNull(){
        LoanService service = new LoanService(loanRepository);
        service.createLoan( "def", "Chiranjiv", 1000, 1, BigDecimal.valueOf(2));
        BigDecimal response = service.totalAmountPaidTillEmi("Chiranjiv", "def", 0);

        assertNotNull(response);
        assertEquals(BigDecimal.valueOf(0.0), response);
    }

    @Test
    public void remainingEmis_LoanDoesNotExist_shouldReturnNull(){
        LoanService service = new LoanService(loanRepository);
        Integer response = service.remainingEmis("Chiranjiv", "def", 2);

        assertNull(response);
    }

    @Test
    public void remainingEmis_LoanDoesExist_shouldReturnNonNull(){
        LoanService service = new LoanService(loanRepository);
        service.createLoan( "def", "Chiranjiv", 1000, 1, BigDecimal.valueOf(2));
        Integer response = service.remainingEmis("Chiranjiv", "def", 1);
        assertNotNull(response);
    }

    @Test
    public void lumpSumAmountPaidTillEmiNumber_LoanDoesNotExist_shouldReturnZero(){
        LoanService service = new LoanService(loanRepository);
        Integer response = service.lumpSumAmountPaidTillEmiNumber("Chiranjiv", "def", 2);
        assertEquals(0, response);
    }

    @Test
    public void lumpSumAmountPaidTillEmiNumber_LoanExist_shouldReturnNonZero(){
        LoanService service = new LoanService(loanRepository);
        service.createLoan( "def", "Chiranjiv", 1000, 1, BigDecimal.valueOf(2));
        service.makeRepayment("def", "Chiranjiv", 500, 1);
        Integer response = service.lumpSumAmountPaidTillEmiNumber("Chiranjiv", "def", 2);
        assertNotEquals(0, response);
    }

}
