function findGreater(a, b) {
    if (a > b) return `${a} is greater than ${b}`;
    if (b > a) return `${b} is greater than ${a}`;
    return 'Both numbers are equal';
}

// Example usage
console.log(findGreater(5, 10));  // "10 is greater than 5"
console.log(findGreater(15, 3));  // "15 is greater than 3"
console.log(findGreater(7, 7));   // "Both numbers are equal"