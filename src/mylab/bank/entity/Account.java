package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public abstract class Account {
    private final String accountNumber;
    private String ownerName;
    private double balance;

    protected Account(String accountNumber, String ownerName, double initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException("초기 잔액은 음수가 될 수 없습니다.");
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("입금액은 0보다 커야 합니다.");
        balance += amount;
        System.out.printf("%.1f원이 입금되었습니다. 현재 잔액: %.1f원%n", amount, balance);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) throw new IllegalArgumentException("출금액은 0보다 커야 합니다.");
        if (balance < amount) throw new InsufficientBalanceException("잔액이 부족합니다. 현재 잔액: " + balance + "원");
        balance -= amount;
        System.out.printf("%.1f원이 출금되었습니다. 현재 잔액: %.1f원%n", amount, balance);
    }

    @Override
    public String toString() {
        return String.format("계좌번호: %s, 소유자: %s, 잔액: %.1f원",
                accountNumber, ownerName, balance);
    }
}