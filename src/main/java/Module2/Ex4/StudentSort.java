package Module2.Ex4;

import Module2.Ex3.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class StudentSort {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Иван", 20, 8.5));
        students.add(new Student("Анна", 19, 9.2));
        students.add(new Student("Петр", 21, 7.8));
        students.add(new Student("Мария", 20, 8.9));
        students.add(new Student("Алексей", 22, 6.5));
        students.add(new Student("Ольга", 19, 9.5));
        students.add(new Student("Сергей", 23, 8.1));

        // Сортировка по имени
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });

        System.out.println("\nПосле сортировки по имени");
        for (Student student : students) {
            System.out.println(student);
        }

        // Сортировка по среднему баллу
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getAverageGrade(), s1.getAverageGrade());
            }
        });

        System.out.println("\nСортировка по среднему баллу");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
