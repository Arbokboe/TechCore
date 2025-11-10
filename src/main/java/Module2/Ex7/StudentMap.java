package Module2.Ex7;

import Module2.Ex3.Student;

import java.util.HashMap;

public class StudentMap {
    private HashMap<String, Student> studentMap;

    public StudentMap() {
        studentMap = new HashMap<>();
    }

    public void addStudent(String studentId, Student student) {
        studentMap.put(studentId, student);
    }

    public Student findStudent(String studentId) {
        return studentMap.get(studentId);
    }

    public Student removeStudent(String studentId) {
        return studentMap.remove(studentId);
    }

    public void printAllStudents() {
        for (String id : studentMap.keySet()) {
            System.out.println("ID: " + id + " -> " + studentMap.get(id));
        }
    }

    public static void main(String[] args) {
        StudentMap studentManager = new StudentMap();

        studentManager.addStudent("S001", new Student("Иван", 20, 8.5));
        studentManager.addStudent("S002", new Student("Анна", 19, 9.2));
        studentManager.addStudent("S003", new Student("Петр", 21, 7.8));
        studentManager.addStudent("S004", new Student("Мария", 20, 8.9));

        System.out.println("Все студенты:");
        studentManager.printAllStudents();


        System.out.println("\nПоиск студента с ID S002:");
        Student foundStudent = studentManager.findStudent("S002");
        System.out.println(foundStudent != null ? foundStudent : "Студент не найден");


        System.out.println("\nПоиск студента с ID S999:");
        Student notFoundStudent = studentManager.findStudent("S999");
        System.out.println(notFoundStudent != null ? notFoundStudent : "Студент не найден");

        System.out.println("\nУдаляем студента с ID S003:");
        Student removedStudent = studentManager.removeStudent("S003");
        System.out.println("Удален: " + removedStudent);

        System.out.println("\nОставшиеся студенты:");
        studentManager.printAllStudents();
    }
}
