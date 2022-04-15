package in.ledger.factory;

import in.ledger.enums.RepositoryType;
import in.ledger.repository.IRepository;
import in.ledger.repository.LoanRepository;

public class LoanDatastoreFactory {

    public static IRepository getRepo(RepositoryType type){
        switch (type){
            case Loan:
                return new LoanRepository();
        }

        return null;
    }
}
