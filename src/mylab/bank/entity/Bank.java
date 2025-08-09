package mylab.bank.entity;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank {
    private final List<Account> accounts = new ArrayList<>();
    private int nextAccountNumber = 1000;

    private String generateAccountNumber() {
        return "AC" + (nextAccountNumber++);
    }

    public SavingsAccount createSavingsAccount(String ownerName, double initialBalance, double interestRate) {
        String no = generateAccountNumber();
        SavingsAccount acc = new SavingsAccount(no, ownerName, initialBalance, interestRate);
        accounts.add(acc);
        System.out.printf("Saving(저축) 계좌가 생성되었습니다: %s%n", describeAccount(acc));
        return acc;
    }

    public CheckingAccount createCheckingAccount(String ownerName, double initialBalance, double withdrawalLimit) {
        String no = generateAccountNumber();
        CheckingAccount acc = new CheckingAccount(no, ownerName, initialBalance, withdrawalLimit);
        accounts.add(acc);
        System.out.printf("체킹 계좌가 생성되었습니다: %s%n", describeAccount(acc));
        return acc;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accountNumber)) return a;
        }
        throw new AccountNotFoundException("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        findAccount(accountNumber).withdraw(amount);
    }

    public double applyInterest(String savingsAccountNumber) throws AccountNotFoundException {
        Account a = findAccount(savingsAccountNumber);
        if (!(a instanceof SavingsAccount)) {
            throw new IllegalArgumentException("이자 적용은 저축 계좌에만 가능합니다.");
        }
        return ((SavingsAccount) a).applyInterest();
    }

    public void transfer(String fromAccNo, String toAccNo, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        if (amount <= 0) throw new IllegalArgumentException("이체 금액은 0보다 커야 합니다.");
        Account from = findAccount(fromAccNo);
        Account to = findAccount(toAccNo);

        from.withdraw(amount); 
        to.deposit(amount);
        System.out.printf("%.1f원이 %s에서 %s로 송금되었습니다.%n", amount, fromAccNo, toAccNo);
    }

    private String describeAccount(Account a) {
        if (a instanceof SavingsAccount) {
            SavingsAccount s = (SavingsAccount) a;
            return String.format("계좌번호: %s, 소유자: %s, 잔액: %.1f원, 이자율: %.1f%%",
                    s.getAccountNumber(), s.getOwnerName(), s.getBalance(), s.getInterestRate());
        } else if (a instanceof CheckingAccount) {
            CheckingAccount c = (CheckingAccount) a;
            return String.format("계좌번호: %s, 소유자: %s, 잔액: %.1f원, 출금 한도: %.1f원",
                    c.getAccountNumber(), c.getOwnerName(), c.getBalance(), c.getWithdrawalLimit());
        }
        return a.toString();
    }

    public List<Account> getAllAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void printAllAccounts() {
        System.out.println("=== 모든 계좌 목록 ===");
        for (Account a : accounts) {
            System.out.println(describeAccount(a));
        }
        System.out.println("===================");
    }
}