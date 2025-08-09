package workshop.oop.flexible;

/*
 * 추상클래스는 객체를 생성할 수 없다. 
 * Employee e = new Employee(); (x)
 * Employee m = new Manger(); (O)
 * 추상메서드를 하나 이상 가지고 있으면 무조건 추상클래스가 된다.
 * Super 클래스의 메서드가  추상메서드이면 , Sub 클래스에서 그 추상메서드를 
 * 반드시 오버라이딩 해야 한다. 
 */
public abstract class Employee extends Object {

	private String name;
	protected double salary;

	public Employee() {
	}
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	//Concrete Method
	public String getName() {
	    return this.name;
	}

	public double getSalary() {
	    return this.salary;
	}

	//Abstract Method
	public abstract void manageSalary(double rate);
}