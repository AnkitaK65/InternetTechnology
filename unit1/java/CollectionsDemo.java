package unit1.java;

import java.util.*;
import java.util.Map.Entry;

//Java code demonstrating various Java Collections framework features,
//including ArrayList, LinkedList, HashSet, HashMap, and common operations
public class CollectionsDemo {
    public static void main(String[] args) {
        System.out.println("----- ArrayList Demo -----");
        arrayListDemo();
        
        System.out.println("\n----- LinkedList Demo -----");
        linkedListDemo();
        
        System.out.println("\n----- HashSet Demo -----");
        hashSetDemo();
        
        System.out.println("\n----- HashMap Demo -----");
        hashMapDemo();
        
        System.out.println("\n----- Collections Utility Methods -----");
        collectionsUtilityMethods();
    }
    
    // ArrayList demonstration
    public static void arrayListDemo() {
        ArrayList<String> fruits = new ArrayList<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add(1, "Mango"); // Insert at specific position
        
        System.out.println("ArrayList: " + fruits);
        System.out.println("Size: " + fruits.size());
        System.out.println("Element at index 2: " + fruits.get(2));
        
        // Iteration methods
        System.out.println("\nIterating with for-each:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        
        System.out.println("\nIterating with iterator:");
        Iterator<String> it = fruits.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        
        // Removing elements
        fruits.remove("Banana");
        fruits.remove(0);
        System.out.println("\nAfter removal: " + fruits);
    }
    
    // LinkedList demonstration
    public static void linkedListDemo() {
        LinkedList<Integer> numbers = new LinkedList<>();
        
        // Adding elements
        numbers.add(10);
        numbers.add(20);
        numbers.addFirst(5);  // Add at beginning
        numbers.addLast(30);  // Add at end
        
        System.out.println("LinkedList: " + numbers);
        System.out.println("First element: " + numbers.getFirst());
        System.out.println("Last element: " + numbers.getLast());
        
        // Queue operations
        System.out.println("\nQueue operations:");
        System.out.println("Poll (remove and return first): " + numbers.poll());
        System.out.println("After poll: " + numbers);
        
        // Stack operations
        System.out.println("\nStack operations:");
        numbers.push(15);  // Push to front (like stack)
        System.out.println("After push: " + numbers);
        System.out.println("Pop: " + numbers.pop());
        System.out.println("After pop: " + numbers);
    }
    
    // HashSet demonstration
    public static void hashSetDemo() {
        HashSet<String> countries = new HashSet<>();
        
        // Adding elements (duplicates are ignored)
        countries.add("India");
        countries.add("USA");
        countries.add("UK");
        countries.add("India");  // Duplicate
        
        System.out.println("HashSet: " + countries);
        System.out.println("Contains 'USA'? " + countries.contains("USA"));
        
        // Iteration (order is not guaranteed)
        System.out.println("\nIterating HashSet:");
        for (String country : countries) {
            System.out.println(country);
        }
        
        // Remove element
        countries.remove("UK");
        System.out.println("\nAfter removal: " + countries);
    }
    
    // HashMap demonstration
    public static void hashMapDemo() {
        HashMap<Integer, String> studentMap = new HashMap<>();
        
        // Adding key-value pairs
        studentMap.put(101, "Alice");
        studentMap.put(102, "Bob");
        studentMap.put(103, "Charlie");
        studentMap.put(104, "Diana");
        
        System.out.println("HashMap: " + studentMap);
        System.out.println("Value for key 103: " + studentMap.get(103));
        System.out.println("Contains key 105? " + studentMap.containsKey(105));
        
        // Iteration methods
        System.out.println("\nIterating using entrySet():");
        for (Entry<Integer, String> entry : studentMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        
        System.out.println("\nIterating using keySet():");
        for (Integer key : studentMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + studentMap.get(key));
        }
        
        // Remove a mapping
        studentMap.remove(102);
        System.out.println("\nAfter removal: " + studentMap);
    }
    
    // Collections utility methods
    public static void collectionsUtilityMethods() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        
        System.out.println("Original list: " + numbers);
        
        // Sorting
        Collections.sort(numbers);
        System.out.println("After sorting: " + numbers);
        
        // Reversing
        Collections.reverse(numbers);
        System.out.println("After reversing: " + numbers);
        
        // Shuffling
        Collections.shuffle(numbers);
        System.out.println("After shuffling: " + numbers);
        
        // Binary search (list must be sorted)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 8);
        System.out.println("Index of 8: " + index);
        
        // Min and max
        System.out.println("Min value: " + Collections.min(numbers));
        System.out.println("Max value: " + Collections.max(numbers));
        
        // Fill
        Collections.fill(numbers, 0);
        System.out.println("After fill with 0: " + numbers);
    }
}

/*
OUTPUT

----- ArrayList Demo -----
ArrayList: [Apple, Mango, Banana, Orange]
Size: 4
Element at index 2: Banana

Iterating with for-each:
Apple
Mango
Banana
Orange

Iterating with iterator:
Apple
Mango
Banana
Orange

After removal: [Mango, Orange]

----- LinkedList Demo -----
LinkedList: [5, 10, 20, 30]
First element: 5
Last element: 30

Queue operations:
Poll (remove and return first): 5
After poll: [10, 20, 30]

Stack operations:
After push: [15, 10, 20, 30]
Pop: 15
After pop: [10, 20, 30]

----- HashSet Demo -----
HashSet: [USA, UK, India]
Contains 'USA'? true

Iterating HashSet:
USA
UK
India

After removal: [USA, India]

----- HashMap Demo -----
HashMap: {101=Alice, 102=Bob, 103=Charlie, 104=Diana}
Value for key 103: Charlie
Contains key 105? false

Iterating using entrySet():
Key: 101, Value: Alice
Key: 102, Value: Bob
Key: 103, Value: Charlie
Key: 104, Value: Diana

Iterating using keySet():
Key: 101, Value: Alice
Key: 102, Value: Bob
Key: 103, Value: Charlie
Key: 104, Value: Diana

After removal: {101=Alice, 103=Charlie, 104=Diana}

----- Collections Utility Methods -----
Original list: [5, 2, 8, 1, 9, 3]
After sorting: [1, 2, 3, 5, 8, 9]
After reversing: [9, 8, 5, 3, 2, 1]
After shuffling: [5, 9, 3, 8, 2, 1]
Index of 8: 4
Min value: 1
Max value: 9
After fill with 0: [0, 0, 0, 0, 0, 0]
 */