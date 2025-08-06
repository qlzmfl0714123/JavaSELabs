package mylab.student.control;

import mylab.student.entity.Student;
import mylab.student.exception.InvalidGradeException;

public class StudentTest {
    public static void main(String[] args) {
        try {
            Student s1 = new Student("20250001", "김민수", "컴퓨터공학", 3);
            System.out.println(s1);

            System.out.println("학년을 5로 변경 시도");
            s1.setGrade(5);  

        } catch (InvalidGradeException e) {
            System.out.println(e.getMessage());
        }
    }
}