package workshop.book.control;

import workshop.book.entity.Magazine;
import workshop.book.entity.Novel;
import workshop.book.entity.Publication;
import workshop.book.entity.ReferenceBook;

public class ManageBook {

	public static void main(String[] args) {
		//Publication 타입의 배열을 선언 및 생성
		Publication[] pubs = new Publication[5];
		pubs[0] = new Magazine("마이크로소프트","2007-10-01",328,9900,"매월");
		pubs[1] = new Magazine("경영과컴퓨터","2007-10-03",316,9000,"매월");
		pubs[2] = new Novel("빠삐용","2007-07-01",396,9800,"베르나르베르베르","현대소설");
		pubs[3] = new Novel("남한산성","2007-04-14",383,11000,"김훈","대하소설");
		pubs[4] = new ReferenceBook("실용주의프로그래머","2007-01-14",496,25000,"소프트웨어공학");	

		System.out.println("==== Book 정보 출력 ====");
		for(Publication pub : pubs) {
			System.out.println(pub);  //pub.toString()
		}
	}
	
	//Polymorphic Argument ( 다형적인 아규먼트 )
	public static void modifyPrice(Publication pub) {
		//Magazine이면 40%, Novel이면 20%, ReferenceBook 이면 10%
		double rate = 0.0;
		if(pub instanceof Magazine) {
			rate = 0.6; //40%
		}
		if(pub instanceof Novel) {
			rate = 0.8; //20%
		}
		
		if(pub instanceof ReferenceBook) {
			rate = 0.9; //10%
		}
		//가격 변경
		pub.setPrice((int)(pub.getPrice() * rate));
		
	}
	
	
	

}