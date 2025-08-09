package mylab.bank.entity;

public class SavingsAccount extends Account {
    private double interestRate; 

    public SavingsAccount(String accountNumber, String ownerName, double initialBalance, double interestRate) {
        super(accountNumber, ownerName, initialBalance);
        if (interestRate < 0) throw new IllegalArgumentException("이자율은 음수가 될 수 없습니다.");
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) {
        if (interestRate < 0) throw new IllegalArgumentException("이자율은 음수가 될 수 없습니다.");
        this.interestRate = interestRate;
    }

    public double applyInterest() {
        double interest = getBalance() * (interestRate / 100.0);
        
        deposit(interest);
        System.out.printf("이자 %.1f원이 적용되었습니다. 현재 잔액: %.1f원%n", interest, getBalance());
        return interest;
    }

    @Override
    public String toString() {
        return String.format("%s, 이자율: %.1f%%", super.toString(), interestRate);
    }
}