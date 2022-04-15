# Project Description

This is a boilerplate code for the java based implementation of ledger
problem statement described below.

This code will be given to a candidate coming for an onsite interview so that
they can take the implementation forward.

## Requirements

The project is built using Java 1.8. It uses Maven as the build system.

1. Java - 1.8.x
2. Maven - 3.x.x

## Building the application using maven

You can build and package the application in the form of a jar file using maven -

```sh
cd ledger
mvn clean package
```

The above command will produce a standalone jar file named `application.jar` in
the `ledger/target` directory.

## Running tests

The `mvn package` command runs all the unit tests and also packages the
application in the form of a jar file. If you just want to run the tests without
packaging it, then you can use `mvn test` command.

```sh
cd ledger
mvn test
```

## Running the application

You can run the jar file created by the `mvn package` command like so -

```sh
java -jar target/application.jar
```

The above command launches an interactive shell where you can type various
parking lot commands and receive the output on the console.

The application can also take input commands from a file. You can pass multiple
commands separated by a newline in the file like so -

```sh
java -jar target/application.jar file_input.txt
```

# Problem Statement

You work at a startup called The Ledger Co., a marketplace for banks to lend money to borrowers and receive payments for the loans. The interest for the loan is calculated by I = P*N*R where P is the principal amount, N is the number of years and R is the rate of interest. The total amount to repay will be A = P + I The amount should be paid back monthly in the form of EMIs. The borrowers can also pay a lump sum (that is, an amount more than their monthly EMI). In such a case, the lump sum will be deducted from the total amount (A) which can reduce the number of EMIs. This doesn’t affect the EMI amount unless the remaining total amount is less than the EMI. All transactions happen through The Ledger Co. You need to design a system to find out how much amount a user has paid the bank and how many EMIs are remaining.

Your program should take as input:
1. The bank name, borrower name, principal, interest and term.
2. Lump sum payments if any.
3. Given the bank name, borrower name, and EMI number, the program should print the total amount paid by the user (including the EMI number mentioned) and the remaining number of EMIs.

The output should be
1. Amount paid so far, and number of EMIs remaining for the user with the bank

## Commands to be implemented

- `LOAN`
- `PAYMENT`
- `BALANCE`


<h3>LOAN</h3>
The LOAN command receives a Bank name, Borrower name, Principal Amount, No of Years of Loan period and the Rate of Interest along with it.

```sh 
Format - LOAN BANK_NAME BORROWER_NAME PRINCIPAL NO_OF_YEARS RATE_OF_INTEREST
Example- LOAN IDIDI Dale 10000 5 4 means a loan amount of 10000 is paid to Dale by IDIDI for a tenure of 5 years at 4% rate of interest```
```

<h3>PAYMENT</h3>
The PAYMENT command receives a Bank name, Borrower name, Lump sum amount and EMI number along with it. The EMI number indicates that the lump sum payment is done after that EMI.
```sh
Format - PAYMENT BANK_NAME BORROWER_NAME LUMP_SUM_AMOUNT EMI_NO
Example - PAYMENT MBI Dale 1000 5 means a lump sum payment of 1000 was done by Dale to MBI after 5 EMI payments.
```


<h3>BALANCE</h3>
The BALANCE command receives a Bank name, Borrower name and a EMI number along with it. It prints the total amount paid by the borrower, including all the Lump Sum amounts paid including that EMI number, and the no of EMIs remaining.

```sh
Input format - BALANCE BANK_NAME BORROWER_NAME EMI_NO
Example - BALANCE MBI Harry 12 means - print the amount paid including 12th EMI, and EMIs remaining for user Harry against the lender MBI.

Output format - BANK_NAME BORROWER_NAME AMOUNT_PAID NO_OF_EMIS_LEFT
Example - MBI Harry 1250 43
```

### Assumptions
1. Repayments will be paid every month as EMIs until the total amount is recovered.
2. Lump sum amounts can be paid at any point of time before the end of tenure.
3. The EMI amount will be always ceiled to the nearest integer. For example 86.676767 can be ceiled to 87 and 100.10 to 101.
4. The no of EMIs should be ceiled to the nearest whole number. For example 23.79 will be ceiled to 24 and 23.1 will also be ceiled to 24.
5. If the last EMI is more than the remaining amount to pay, then it should be adjusted to match the amount remaining to pay. E.g. if the remaining amount to pay is 150 and your EMI is 160, then the last EMI amount paid should be 150.
6. The total amount remaining at any EMI number should always include the EMIs paid and lump sum payments until that number. For example if there was a lump sum payment after EMI number 10, then the balance command for EMI number 10 should include the lump sum payment as well.

## Batch mode test

To run the code so it accepts input from a file:

<h4>Sample Test -1:</h4>
<b>input</b>
```
LOAN IDIDI Dale 10000 5 4
LOAN MBI Harry 2000 2 2
BALANCE IDIDI Dale 5
BALANCE IDIDI Dale 40
BALANCE MBI Harry 12
BALANCE MBI Harry 0
```

<b>Output:</b>
```
IDIDI Dale 1000 55
IDIDI Dale 8000 20
MBI Harry 1044 12
MBI Harry 0 24
```

<h4>Sample Test -2:</h4>
<b>input</b>
```
LOAN IDIDI Dale 5000 1 6
LOAN MBI Harry 10000 3 7
LOAN UON Shelly 15000 2 9
PAYMENT IDIDI Dale 1000 5
PAYMENT MBI Harry 5000 10
PAYMENT UON Shelly 7000 12
BALANCE IDIDI Dale 3
BALANCE IDIDI Dale 6
BALANCE UON Shelly 12
BALANCE MBI Harry 12
```

<b>Output:</b>
```
IDIDI Dale 1326 9
IDIDI Dale 3652 4
UON Shelly 15856 3
MBI Harry 9044 10
```