package Ex7;

public class Student {
    private String name;
    private int age;
    private double averageGrade;

    public Student(String name, int age, double averageGrade) {
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public static void main(String[] args) {
        Student student1 = new Student("Иван", 20, 4.5);
        Student student2 = new Student("Мария", 19, 4.8);
        Student student3 = new Student("Петр", 21, 4.2);

        Student bestStudent = student1;
        if (student2.getAverageGrade() > bestStudent.getAverageGrade()) {
            bestStudent = student2;
        }
        if (student3.getAverageGrade() > bestStudent.getAverageGrade()) {
            bestStudent = student3;
        }

        System.out.println("Самый успешный студент:");
        System.out.println("Имя: " + bestStudent.getName());
        System.out.println("Возраст: " + bestStudent.getAge());
        System.out.println("Средний балл: " + bestStudent.getAverageGrade());
    }
}