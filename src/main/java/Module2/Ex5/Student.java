package Module2.Ex5;

class Student extends Person {
    private double averageGrade;

    public Student(String name, int age, double averageGrade) {
        super(name, age);
        this.averageGrade = averageGrade;
    }

    @Override
    public void introduce() {
        System.out.println("Я студент. Меня зовут " + getName() + ", мне " + getAge() + " лет. Мой средний балл: " + averageGrade);
    }
}
