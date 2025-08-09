package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.entity.SavingsAccount;
import mylab.bank.entity.CheckingAccount;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class BankDemo {

    public static void main(String[] args) {
        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");
        SavingsAccount ac1 = bank.createSavingsAccount("홍길동", 10000, 3.0);     
        CheckingAccount ac2 = bank.createCheckingAccount("김철수", 20000, 5000); 
        SavingsAccount ac3 = bank.createSavingsAccount("이영희", 30000, 2.0); 
        System.out.println();

        bank.printAllAccounts();

        System.out.println();
        System.out.println("=== 입금/출금 테스트 ===");
        try {
            bank.deposit(ac1.getAccountNumber(), 5000);  
            bank.withdraw(ac2.getAccountNumber(), 3000); 
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== 이자 적용 테스트 ===");
        try {
            bank.applyInterest(ac1.getAccountNumber());   
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== 계좌 이체 테스트 ===");
        try {
            bank.transfer(ac3.getAccountNumber(), ac2.getAccountNumber(), 5000); // AC1002 -> AC1001
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println();
        bank.printAllAccounts();

        try {
            bank.withdraw(ac2.getAccountNumber(), 6000);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            bank.transfer(ac2.getAccountNumber(), ac1.getAccountNumber(), 6000);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            bank.deposit("AC9999", 1000);
        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}