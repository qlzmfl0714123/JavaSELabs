package mylab.bank.exception;

public class WithdrawalLimitExceededException extends InsufficientBalanceException {
    public WithdrawalLimitExceededException(double limit) {
        super(String.format("��� �ѵ��� �ʰ��߽��ϴ�. �ѵ�: %.1f��", limit));
    }
}