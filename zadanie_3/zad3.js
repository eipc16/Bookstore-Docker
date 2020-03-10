function sum(numbers) {
    return numbers.map(number => parseInt(number))
        .map(number => number % 2 == 0 ? number - 1 : number + 1)
        .reduce((a, b) => a + b, 0)
}

console.log(sum(process.argv.slice(2)))