package unit1.java;

import java.util.Arrays;

// Demonstrating Array usage in Java
public class ArrayExample {
    public static void main(String[] args) {
        // Declare and initialize an array
        int[] numbers = {10, 20, 30, 40, 50};

        //Java prints the class name + hash code: 
        System.out.println("Directly Priniting Array: " + numbers);
        //Output : [I@6d06d69c
        //[I -> array of integer type
        //@6d06d69c -> hash code of the object in hexadecimal
        // Access array elements
        System.out.println("First element: " + numbers[0]);
        System.out.println("Array length: " + numbers.length);
        
        // Iterate through array
        System.out.println("All elements:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Element " + i + ": " + numbers[i]);
        }
        
        // Modify array element
        numbers[2] = 35;
        System.out.println("Modified third element: " + numbers[2]);

                //To display the elements of the array
        System.out.println("Array contents: " + Arrays.toString(numbers));
    }
}

/*
OUTPUT

Directly Priniting Array: [I@6d06d69c
First element: 10
Array length: 5
All elements:
Element 0: 10
Element 1: 20
Element 2: 30
Element 3: 40
Element 4: 50
Modified third element: 35
Array contents: [10, 20, 35, 40, 50]
 */