package Module2.Ex5;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void introduce() {
        System.out.println("Меня зовут " + name + ", мне " + age + " лет.");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        Person[] people = new Person[4];

        people[0] = new Student("Иван", 20, 8.5);
        people[1] = new Teacher("Мария Ивановна", 45, "математику");
        people[2] = new Student("Анна", 19, 9.2);
        people[3] = new Teacher("Петр Сергеевич", 50, "физику");

        for (Person person : people) {
            person.introduce();
        }
    }
}
