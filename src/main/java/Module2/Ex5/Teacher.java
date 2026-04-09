package Module2.Ex5;

class Teacher extends Person {
    private String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    public void introduce() {
        System.out.println("Я учитель. Меня зовут " + getName() + ", мне " + getAge() + " лет. Я преподаю " + subject);
    }
}
