function factorial(n) {
    if (n < 0) return "Input must be non-negative";
    if (n === 0 || n === 1) return 1;
    
    let result = 1;
    for (let i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

// Example usage
console.log(factorial(5));  // 120
console.log(factorial(0));  // 1
console.log(factorial(7));  // 5040