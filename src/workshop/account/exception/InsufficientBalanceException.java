package workshop.account.exception;

public class InsufficientBalanceException extends Exception{
	public InsufficientBalanceException(String errMessage) {
		//부모클래스(Exception)의 생성자를 호출하기
		super(errMessage);
	}
	
}