function sum(numbers) {
    let result = 0,
        currentValue;
    for (let i = 0; i < numbers.length; i++) {
        currentValue = parseInt(numbers[i])
        if (currentValue % 2 == 0) {
            result += currentValue - 1
        } else {
            result += currentValue + 1
        }
    }
    return result
}

print(sum(arguments))