package mylab.bank.entity;

public class SavingsAccount extends Account {
    private double interestRate; 

    public SavingsAccount(String accountNumber, String ownerName, double initialBalance, double interestRate) {
        super(accountNumber, ownerName, initialBalance);
        if (interestRate < 0) throw new IllegalArgumentException("�������� ������ �� �� �����ϴ�.");
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) {
        if (interestRate < 0) throw new IllegalArgumentException("�������� ������ �� �� �����ϴ�.");
        this.interestRate = interestRate;
    }

    public double applyInterest() {
        double interest = getBalance() * (interestRate / 100.0);
        
        deposit(interest);
        System.out.printf("���� %.1f���� ����Ǿ����ϴ�. ���� �ܾ�: %.1f��%n", interest, getBalance());
        return interest;
    }

    @Override
    public String toString() {
        return String.format("%s, ������: %.1f%%", super.toString(), interestRate);
    }
}