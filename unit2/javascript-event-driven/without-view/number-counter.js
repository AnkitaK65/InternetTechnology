function countNumbers(numbers) {
    let positive = 0, negative = 0, zero = 0;
    
    numbers.forEach(num => {
        if (num > 0) positive++;
        else if (num < 0) negative++;
        else zero++;
    });
    
    return { positive, negative, zero };
}

// Example usage
const numbers = [5, -3, 0, 10, -2, 0, 7];
const counts = countNumbers(numbers);
console.log(`Positive: ${counts.positive}, Negative: ${counts.negative}, Zero: ${counts.zero}`);