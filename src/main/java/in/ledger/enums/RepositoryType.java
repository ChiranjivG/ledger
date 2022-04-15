package in.ledger.enums;

public enum RepositoryType {
    Loan(1);
    int value;
    public int id(){
        return this.value;
    }
    RepositoryType(int value){this.value = value;}
}
