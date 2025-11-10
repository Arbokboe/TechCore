package Module2.Ex2;

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
}