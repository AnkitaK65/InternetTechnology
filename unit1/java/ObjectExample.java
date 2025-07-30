package unit1.java;

// Demonstrating Object usage in Java
public class ObjectExample {
    public static void main(String[] args) {
        // Create a Person object
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Bob", 30);
        
        // Use object methods
        person1.introduce();
        person2.introduce();
        
        // Modify object properties
        person1.setAge(26);
        System.out.println(person1.getName() + "'s new age: " + person1.getAge());
    }
}

class Person {
    private String name;
    private int age;
    
    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Methods
    public void introduce() {
        System.out.println("Hello, I'm " + name + " and I'm " + age + " years old.");
    }
    
    // Getters and setters
    public String getName() { return name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

/*
OUTPUT

Hello, I'm Alice and I'm 25 years old.
Hello, I'm Bob and I'm 30 years old.
Alice's new age: 26
 */