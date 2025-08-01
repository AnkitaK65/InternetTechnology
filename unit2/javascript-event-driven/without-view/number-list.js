function calculateSumAndAverage(numbers) {
    // Assuming the list ends with 0
    const numList = numbers.slice(0, -1);
    const sum = numList.reduce((acc, num) => acc + num, 0);
    const average = sum / numList.length;
    
    return { sum, average };
}

// Example usage
const numbers = [5, 10, 15, 20, 0];
const result = calculateSumAndAverage(numbers);
console.log(`Sum: ${result.sum}, Average: ${result.average}`);