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
        System.out.printf("Saving(����) ���°� �����Ǿ����ϴ�: %s%n", describeAccount(acc));
        return acc;
    }

    public CheckingAccount createCheckingAccount(String ownerName, double initialBalance, double withdrawalLimit) {
        String no = generateAccountNumber();
        CheckingAccount acc = new CheckingAccount(no, ownerName, initialBalance, withdrawalLimit);
        accounts.add(acc);
        System.out.printf("üŷ ���°� �����Ǿ����ϴ�: %s%n", describeAccount(acc));
        return acc;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accountNumber)) return a;
        }
        throw new AccountNotFoundException("���¹�ȣ " + accountNumber + "�� �ش��ϴ� ���¸� ã�� �� �����ϴ�.");
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
            throw new IllegalArgumentException("���� ������ ���� ���¿��� �����մϴ�.");
        }
        return ((SavingsAccount) a).applyInterest();
    }

    public void transfer(String fromAccNo, String toAccNo, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        if (amount <= 0) throw new IllegalArgumentException("��ü �ݾ��� 0���� Ŀ�� �մϴ�.");
        Account from = findAccount(fromAccNo);
        Account to = findAccount(toAccNo);

        from.withdraw(amount); 
        to.deposit(amount);
        System.out.printf("%.1f���� %s���� %s�� �۱ݵǾ����ϴ�.%n", amount, fromAccNo, toAccNo);
    }

    private String describeAccount(Account a) {
        if (a instanceof SavingsAccount) {
            SavingsAccount s = (SavingsAccount) a;
            return String.format("���¹�ȣ: %s, ������: %s, �ܾ�: %.1f��, ������: %.1f%%",
                    s.getAccountNumber(), s.getOwnerName(), s.getBalance(), s.getInterestRate());
        } else if (a instanceof CheckingAccount) {
            CheckingAccount c = (CheckingAccount) a;
            return String.format("���¹�ȣ: %s, ������: %s, �ܾ�: %.1f��, ��� �ѵ�: %.1f��",
                    c.getAccountNumber(), c.getOwnerName(), c.getBalance(), c.getWithdrawalLimit());
        }
        return a.toString();
    }

    public List<Account> getAllAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void printAllAccounts() {
        System.out.println("=== ��� ���� ��� ===");
        for (Account a : accounts) {
            System.out.println(describeAccount(a));
        }
        System.out.println("===================");
    }
}