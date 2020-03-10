function createAccount(spec) {
    var balance = 0,
        accountNumber = spec.accountNumber,
        currency = spec.currency,
        creationDate = spec.creationDate;
    return Object.freeze({
        debit: function(amount) {
            if (amount < 0) {
                throw new Error('Illegal amount');
            }
            if (balance < amount) {
                throw new Error('Insufficient account balance');
            }
            balance -= amount;
            return balance;
        },
        credit: function(amount) {
            if (amount < 0) {
                throw new Error('Illegal amount');
            }
            balance += amount;
            return balance;
        },
        getData: function() {
            return { balance, accountNumber, currency, creationDate }
        }
    })
}

let date = new Date();
let account = createAccount({
    // accountNumber: '123',
    // creationDate: date,
    // currency: 'PLN'
})
let account2 = createAccount({
    // accountNumber: '123',
    // creationDate: date,
    // currency: 'PLN'
})

console.log('Zad 1')
console.log('Account 1 (Data): ' + JSON.stringify(account.getData()))
console.log('Account 2 (Data): ' + JSON.stringify(account2.getData()))
console.log('account == account2 -> ' + (account == account2))
console.log('----------\n\n')

console.log('Zad 2')
let account3 = createAccount({
    accountNumber: '111',
    creationDate: new Date(),
    currency: 'USD'
})
console.log('Account 3 (Data):')
console.log(account3.getData())
console.log('----------\n\n')

console.log('Zad 3')
account3.balance = 999999;
account3.accountNumber = 'NEW';
account3.creationDate = null;
account3.currency = 'PLN';
account3.credit(1000);
account3.debit(640);
console.log('Account 3 (After property reassign)')
console.log('Account 3:')
console.log(account3)
console.log('Account 3 (Data):')
console.log(account3.getData())
console.log('----------\n\n')

console.log('Zad 4')
let account4a = createAccount({
    accountNumber: '123',
    creationDate: new Date('2020-03-11'),
    currency: 'GBP'
})
let account4b = createAccount({
    accountNumber: '456',
    creationDate: new Date('2020-03-10'),
    currency: 'USD'
})
let account4c = createAccount({
    accountNumber: '799',
    creationDate: new Date('2020-03-12'),
    currency: 'PLN'
})

console.log('Account 4A (Data):')
console.log(account4a.getData())
console.log('Account 4B (Data):')
console.log(account4b.getData())
console.log('Account 4C (Data):')
console.log(account4c.getData())
console.log('----------\n\n')

console.log('Zad 5')
let account5 = createAccount({
    accountNumber: '2623523623523',
    creationDate: new Date(),
    currency: 'USD'
})
console.log('Account 5 (Data):')
console.log(account5.getData())
console.log('----------')