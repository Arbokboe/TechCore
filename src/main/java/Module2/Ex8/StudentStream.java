package Module2.Ex8;

import Module2.Ex3.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentStream {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Анна", 21, 8.5));
        students.add(new Student("Алексей", 22, 7.8));
        students.add(new Student("Мария", 19, 9.2));
        students.add(new Student("Андрей", 23, 8.9));
        students.add(new Student("Иван", 20, 7.5));
        students.add(new Student("Алина", 24, 9.1));
        students.add(new Student("Петр", 21, 6.8));

        double averageGrade = students.stream()
                .filter(student -> student.getAge() > 20)
                .filter(student -> student.getName().startsWith("А"))
                .mapToDouble(Student::getAverageGrade)
                .average()
                .orElse(0.0);

        System.out.println("Средний балл студентов старше 20 лет с именами на 'А': " + averageGrade);
    }
}
