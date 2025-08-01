function isLeapYear(year) {
    return (year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0);
}

// Example usage
console.log(isLeapYear(2020));  // true
console.log(isLeapYear(2021));  // false
console.log(isLeapYear(2000));  // true
console.log(isLeapYear(1900));  // false