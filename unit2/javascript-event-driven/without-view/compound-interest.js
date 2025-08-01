function calculateCompoundInterest(years, principal = 1000, rate = 0.05) {
    return principal * Math.pow(1 + rate, years);
}

// Example usage
console.log(calculateCompoundInterest(5).toFixed(2));  // 1276.28
console.log(calculateCompoundInterest(10).toFixed(2)); // 1628.89