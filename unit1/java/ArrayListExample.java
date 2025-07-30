package unit1.java;

import java.util.ArrayList;

// Demonstrating ArrayList usage in Java
public class ArrayListExample {
    public static void main(String[] args) {
        // Create an ArrayList
        ArrayList<String> fruits = new ArrayList<>();
        
        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        
        // Access elements
        System.out.println("First fruit: " + fruits.get(0));
        System.out.println("List size: " + fruits.size());
        
        // Iterate through ArrayList
        System.out.println("All fruits:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        
        // Remove element
        fruits.remove(1);
        System.out.println("After removal: " + fruits);
        
        // Check if element exists
        System.out.println("Contains Apple? " + fruits.contains("Apple"));
    }
}

/*
OUTPUT

First fruit: Apple
List size: 3
All fruits:
Apple
Banana
Orange
After removal: [Apple, Orange]
Contains Apple? true
 */