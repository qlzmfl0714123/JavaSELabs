package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private double withdrawalLimit;

    public CheckingAccount(String accountNumber, String ownerName, double initialBalance, double withdrawalLimit) {
        super(accountNumber, ownerName, initialBalance);
        if (withdrawalLimit < 0) throw new IllegalArgumentException("출금 한도는 음수가 될 수 없습니다.");
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() { return withdrawalLimit; }
    public void setWithdrawalLimit(double withdrawalLimit) {
        if (withdrawalLimit < 0) throw new IllegalArgumentException("출금 한도는 음수가 될 수 없습니다.");
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
        return String.format("%s, 출금 한도: %.1f원", super.toString(), withdrawalLimit);
    }
}