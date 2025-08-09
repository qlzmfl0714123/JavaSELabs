package workshop.oop.flexible;
/* 평사원 클래스 */
public class MereClerk extends Employee {    
    public MereClerk (String name, double salary) {
//        this.name = name;
//        this.salary = salary;
    	super(name,salary);
    }
    
    /*
     * 오버라이딩 규칙은 부모가 상속해준 메서드와 Signature가 반드시 같아야 한다.
     * @Override 어노테이션은 오버라이딩 규칙을 컴파일 타임에 체크해준다.
     */
    @Override
    public void manageSalary(double rate) {
        salary = salary+ salary*(rate/100);
    }
}