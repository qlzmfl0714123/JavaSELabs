package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private double withdrawalLimit;

    public CheckingAccount(String accountNumber, String ownerName, double initialBalance, double withdrawalLimit) {
        super(accountNumber, ownerName, initialBalance);
        if (withdrawalLimit < 0) throw new IllegalArgumentException("��� �ѵ��� ������ �� �� �����ϴ�.");
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() { return withdrawalLimit; }
    public void setWithdrawalLimit(double withdrawalLimit) {
        if (withdrawalLimit < 0) throw new IllegalArgumentException("��� �ѵ��� ������ �� �� �����ϴ�.");
        this.withdrawalLimit = withdrawalLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > withdrawalLimit)
            throw new WithdrawalLimitExceededException(withdrawalLimit);
        super.withdraw(amount); 
    }

    @Override
    public String toString() {
        return String.format("%s, ��� �ѵ�: %.1f��", super.toString(), withdrawalLimit);
    }
}