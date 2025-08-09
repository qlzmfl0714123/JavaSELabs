package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public abstract class Account {
    private final String accountNumber;
    private String ownerName;
    private double balance;

    protected Account(String accountNumber, String ownerName, double initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException("�ʱ� �ܾ��� ������ �� �� �����ϴ�.");
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("�Աݾ��� 0���� Ŀ�� �մϴ�.");
        balance += amount;
        System.out.printf("%.1f���� �ԱݵǾ����ϴ�. ���� �ܾ�: %.1f��%n", amount, balance);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) throw new IllegalArgumentException("��ݾ��� 0���� Ŀ�� �մϴ�.");
        if (balance < amount) throw new InsufficientBalanceException("�ܾ��� �����մϴ�. ���� �ܾ�: " + balance + "��");
        balance -= amount;
        System.out.printf("%.1f���� ��ݵǾ����ϴ�. ���� �ܾ�: %.1f��%n", amount, balance);
    }

    @Override
    public String toString() {
        return String.format("���¹�ȣ: %s, ������: %s, �ܾ�: %.1f��",
                accountNumber, ownerName, balance);
    }
}