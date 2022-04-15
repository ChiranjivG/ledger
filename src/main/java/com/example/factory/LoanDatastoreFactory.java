package com.example.factory;

import com.example.enums.RepositoryType;
import com.example.repository.IRepository;
import com.example.repository.LoanRepository;

public class LoanDatastoreFactory {

    public static IRepository getRepo(RepositoryType type){
        switch (type){
            case Loan:
                return new LoanRepository();
        }

        return null;
    }
}
