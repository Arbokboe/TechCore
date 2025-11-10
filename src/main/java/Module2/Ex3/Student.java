package Module2.Ex3;

import java.util.Objects;

public class Student {
    private String name;
    private int age;
    private double averageGrade;

    public Student() {
        this.name = "Неизвестно";
        this.age = 0;
        this.averageGrade = 0.0;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.averageGrade = 0.0;
    }

    public Student(String name, int age, double averageGrade) {
        this.name = name;
        this.age = age;
        setAverageGrade(averageGrade);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        if (averageGrade < 0 || averageGrade > 10) {
            throw new IllegalArgumentException("Оценка должна быть в диапазоне от 0 до 10 " + averageGrade);
        }
        this.averageGrade = averageGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", averageGrade=" + averageGrade + "}";
    }

    public static void main(String[] args) {

        Student student1 = new Student("Иван Иванов", 20, 8.5);
        Student student2 = new Student("Иван Иванов", 20, 9.0);


        Student student3 = new Student("Мария Сидорова", 19, 7.8);
        Student student4 = new Student("Иван Иванов", 21, 8.5);

        // Тест equals
        System.out.println("student1.equals(student2): " + student1.equals(student2));
        System.out.println("student1.equals(student3): " + student1.equals(student3));
        System.out.println("student1.equals(student4): " + student1.equals(student4));

        // Тест hashCode
        System.out.println("student1.hashCode() == student2.hashCode(): " + (student1.hashCode() == student2.hashCode()));
        System.out.println("student1.hashCode() == student3.hashCode(): " + (student1.hashCode() == student3.hashCode()));
    }
}
