package workshop.oop.flexible;

public class FlexibleCompanyDemo {
    public static void main(String[] args) {
        
        MereClerk mereClerk1 = new MereClerk("철수", 100);
        MereClerk mereClerk2 = new MereClerk("영희", 100);
        Manager manager = new Manager("홍길동", 200);
        
        // 자식 mgr = new 자식
        Manager hrMgr = new Manager("둘리",200,"HR");
        System.out.println(hrMgr.getName());
        System.out.println(hrMgr.getSalary());
        System.out.println(hrMgr.getDept());
        
        //다형성(Polymorphism) Poly(다양한) + Morphism(변신,변형)
        Employee itMgr = new Manager("길동",250,"IT");
        System.out.println(itMgr.getName());
        System.out.println(itMgr.getSalary());
        
        //Manager(자식)가 가진 getDept() 메서드 호출하기
        Manager itMgr2 = (Manager)itMgr;
        System.out.println(itMgr2.getDept()); 
        //((Manager)itMgr).getDept();
        
        
        //Heterogeneous Collection  Employee 타입의 배열의 선언하기
        //emps 변수는 Employee[] 타입 ,  emps[0]는 Employee 타입
        Employee[] emps = new Employee[4]; 
        emps[0] = new Manager("길동",250,"IT");
        emps[1] = new MereClerk("철수", 100);
        emps[2] = new MereClerk("영희", 100);
        emps[3] = new Manager("둘리",200,"HR");
        
        System.out.println("현재 월급입니다.");
        printEmployInfo(emps);
        
        for(Employee emp: emps) {
        	emp.manageSalary(10);
        }
        
       System.out.println("올린 후의 월급입니다.");
       printEmployInfo(emps);
                        
   }

	public static void printEmployInfo(Employee[] emps) {
		for(Employee emp: emps) { 
			//emp가 Manager객체로 부터 만들어진 instance 인지를 체크하는 연산자
			if(emp instanceof Manager) {
				//((Manager)emp).getDept();
				System.out.print("관리자 부서명 = " + ((Manager)emp).getDept() + " ");
			}
        	System.out.println(emp.getName() + "의 현재 월급은 " + emp.getSalary() + " 만원 입니다.");        	
        }
	}
}