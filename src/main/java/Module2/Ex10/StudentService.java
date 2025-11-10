package Module2.Ex10;

import Module2.Ex3.Student;

import java.util.HashMap;

class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}

public class StudentService {
    private HashMap<String, Student> studentMap;

    public StudentService() {
        studentMap = new HashMap<>();
    }

    public void addStudent(String studentId, Student student) {
        studentMap.put(studentId, student);
    }

    public Student findStudent(String studentId) throws StudentNotFoundException {
        Student student = studentMap.get(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Студент с ID '" + studentId + "' не найден");
        }
        return student;
    }

    public Student removeStudent(String studentId) throws StudentNotFoundException {
        Student student = studentMap.remove(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Студент с ID '" + studentId + "' не найден для удаления");
        }
        return student;
    }

    public static void main(String[] args) {
        StudentService service = new StudentService();

        service.addStudent("S001", new Student("Иван", 20, 8.5));
        service.addStudent("S002", new Student("Анна", 19, 9.2));

        try {
            Student student = service.findStudent("S001");
            System.out.println("Найден: " + student);

            Student notFound = service.findStudent("S999");
            System.out.println("Этот код не выполнится");
        } catch (StudentNotFoundException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
    }
}
