package workshop.account.entity;

import workshop.account.exception.InsufficientBalanceException;

public class Account extends Object {
	private String custId;
	private String acctId;
	private int balance;

	// default constructor 선언
	public Account() {
		System.out.println("기본생성자 호출됨");
	}

	// Constructor Overloading (생성자 중복정의)
	public Account(String custId, String acctId, int balance) {
		//this.custId = custId;
		//this.acctId = acctId;
		setCustId(custId);
		setAcctId(acctId);
		this.balance = balance;
	}

//	public void setBalance(int balance) {
//		this.balance = balance;
//	}

	public int getBalance() {
		return balance;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getAcctId() {
		return acctId;
	}

	// 입금
	public void deposit(int amount) {
		this.balance += amount;
	}

	// 출금
	public void withdraw(int amount) throws InsufficientBalanceException {
		//잔액부족
		if(amount > this.balance) {
			String errMessage = String.format("잔액이 부족합니다. (요청 금액: %d, 현재 잔액: %d)", amount, this.balance);
			//Exception 강제로 발생 시킴
			throw new InsufficientBalanceException(errMessage);
		}
		this.balance -= amount;
	}
}
