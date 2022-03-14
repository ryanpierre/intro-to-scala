# Mixed Feedback

There are many ways to solve any particular programming problem. Here's an
example:

```javascript

// While Loops
let i = 0;
while (i < 10) {
  console.log(i);
  i++;
}

// For Loops
for (let i = 0; i < 10; i++) {
  console.log(i);
}
```

Which do you like better? 

There are pros and cons, but a lot of it is down to what you are more familiar
with. We could refer to the wide range of different preferences and opinions a
person has as their _programming style._

In software development there are a few big mega-styles. These mega-styles, or
_programming paradigms_ are broad schools of thought when it comes to how you
should solve programming problems.

Here are two examples:

* **Object-Oriented Programming.**  
  This paradigm holds that programs should be broken up into meaningful units
  called objects that contain both state and behaviour.

* **Functional Programming.**  
  This paradigm holds that programs should be broken up into functions that are
  then connected together ('composed') and then called.

There are more than this, but we'll stick to these for the purposes of this
exercise.

Programming languages are typically designed around paradigms. Ruby and Java
prioritise the object-oriented approach. Javascript typically prioritises the
functional approach, and languages like Haskell are even more extreme.

Scala is a multi-paradigm language. That means the language contains all the
features necessary to create a program either functional or object-oriented
approaches.

**Or both.** Scala believes that combining the strengths of both paradigms is
the best approach. 

This makes it a fantastically exciting language. It also means you have to be
careful not to end up with big mess of inconsistent approaches.

## The Exercise

Two programmers have been asked to pair on creating a Bank program. 

This program facilitated deposits and withdrawals to a bank account and then
outputs a bank statement.

However, they got into a big argument about the right paradigm to use. So they
decided to split up. One would write in a functional style, and the other in an
OO style. You can see the results the directories labelled `fnbank` and
`oobank`.

They then reviewed each other's code. Their review is reproduced below.

Your task is to create, with tests, a truly Scala-style mixed paradigm that
takes the best of both approaches. Use the `mixedbank` directories to do this.
You may wish to work through the review point-by-point, or go back and forth.

## The Review

- [Mixed Feedback](#mixed-feedback)
  - [The Exercise](#the-exercise)
  - [The Review](#the-review)
    - [Derived State](#derived-state)
  - [Recursion](#recursion)
  - [Mutable vs Immutable Data Structures](#mutable-vs-immutable-data-structures)
  - [Vals and Vars](#vals-and-vars)

### Derived State

```scala
class Account {
  var balance = 0
  var transactions: ArrayBuffer[Transaction] = ArrayBuffer()

  def deposit(amount: Int, date: LocalDate): Unit = {
    balance += amount
    transactions += new Transaction(amount, date)
  }

  def withdraw(amount: Int, date: LocalDate): Unit = {
    balance -= amount
    transactions += new Transaction(-amount, date)
  }
}
```

> **From Functional Frankie**
> 
> In this `Account` class you're keeping track of the balance and also adding
> transactions to your list.
> 
> This means you have some of what's called 'derived state'. If you removed the
> balance field and the code that updates it, you could still generate the
> balance by looking at all the previous transactions.
>
> This can be a source of bugs through inconsistency! If those two pieces of
> state end up out-of-sync for some reason, who knows what might happen.
> 
> This is really obviously a problem when it comes to your `Statement` class. It
> violates the Single Responsibility Principle to put the balance in every
> transaction, so you need to go ahead and calculate it once more to get the
> Statement to include it!

> **From OO Olive**
> 
> It's true that there's some duplication here, but I'm not so sure about your
> approach either.

What do you think of the derived state here? Is it worth the risk of bugs in a
banking system?

## Recursion

```scala
object StatementFormatter {
  // ...

  private def formatTransactions: Int => Seq[Transaction] => String = (startingBalance: Int) => {
    case Nil => ""
    case tx :: txs =>
      formatTransactions(startingBalance + tx.amount)(txs)
        .concat(formatLine(tx.amount, tx.date, startingBalance + tx.amount))
  }
  
  // ...
}
```

> **From OO Olive**
> 
> This is your solution to generating statement lines with the balance. You set
> up a recursive function that takes a list of transactions sorted from most
> historic to most recent. Then you create a recursive function that:
> 
> 1. First picks the oldest transaction and uses its amount as the starting
>    balance.
> 2. Then creates a statement for all the _other_ transactions, with the initial
>    balance of the oldest transaction's amount.
> 3. Then formats the oldest transaction as a string.
> 4. And plonks it onto the end.
> 
> It works, but there are two problems:
> 
> 1. Recursion has limits in JVM languages like Scala. I think a function can
>    only call itself around... 30,000 times?
> 2. It's completely impenetrable! It took me ten minutes just to read this!
>    That's a big slowdown if everyone on our team has to do that.
> 
> Mine may be a bit crude, but it's easy to understand.

> **From Functional Frankie**
> 
> OK — point taken. But it is pretty fancy code right! And you're not quite
> right about recursion — you're forgetting about tail-call recursion.
> 
> Tail-call recursion is where if the last thing the function does it call
> itself, then the Scala compiler optimises it into a simple loop. No recursion
> limit and no stack overflow!

> **From OO Olive**
> 
> Ah, well I looked into that! There's an annotation called `@tailrec` that you
> can use in Scala to check if it is truly eligible for tail-call recursion.

```scala
//      VVVVVVVV Here is where it goes
private @tailrec def formatTransactions: Int => Seq[Transaction] => String = (startingBalance: Int) => {
  case Nil => ""
  case tx :: txs =>
    formatTransactions(startingBalance + tx.amount)(txs)
      .concat(formatLine(tx.amount, tx.date, startingBalance + tx.amount))
}
```

> And if you add that, you'll see it actually isn't tail recursive! Because your
> call to `formatTransactions` isn't at the end. And it's not a simple fix
> either.

Frankie has certainly run into some trouble here. What do you think? Is it worth
trying to figure out and fix their approach?

## Mutable vs Immutable Data Structures

```scala
class Account {
  var balance = 0
  var transactions: ArrayBuffer[Transaction] = ArrayBuffer()

  def deposit(amount: Int, date: LocalDate): Unit = {
    balance += amount
    transactions += new Transaction(amount, date)
  }

  def withdraw(amount: Int, date: LocalDate): Unit = {
    balance -= amount
    transactions += new Transaction(-amount, date)
  }
}
```

> **From Functional Frankie**  
> 
> You're using an `ArrayBuffer` here. That's a mutable data structure, meaning
> that it can be changed after it is created. You can add and remove items, sort
> them, etc.
> 
> This means someone could do this:

```scala
val account = new Account()
account.transactions.append(new Transaction(-100, LocalDate.now()))
account.balance // This is still 0
```

> **OO Olive:** So don't do that!

> **From Functional Frankie**  
> 
> Someone will eventually, or the transaction `ArrayBuffer` will be passed from
> function to function and someone will try to sort it in place or delete an
> item from it.
> 
> And even if they didn't, wouldn't your program be better if they _couldn't?_
> And no one was up in front of their client explaining how this happened?
> 
> You can do this by using Scala's immutable data structures:

```scala
class Account {
  var balance = 0
  // Replace ArrayBuffer with Seq, which can't be changed.
  var transactions: Seq[Transaction] = Seq()

  def deposit(amount: Int, date: LocalDate): Unit = {
    balance += amount
    // Instead of adding a new item to the Seq, we create a new Seq.
    // 'oldSeq :+ newItem' means 'create a new seq composed of oldSeq plus a new item'
    transactions = transactions :+ new Transaction(amount, date)
    // Or you could do this:
    // transactions = transactions.appended(new Transaction(amount, date))
  }

  def withdraw(amount: Int, date: LocalDate): Unit = {
    balance -= amount
    transactions = transactions :+ new Transaction(-amount, date)
  }
}
```

> Now let's look at what happens in the problematic code:

```scala
val account = new Account()
account.transactions.appended(new Transaction(-100, LocalDate.now()))
account.transactions // This is still an empty Seq!
```

> **OO Olive:** But that makes no sense! You've added a transaction and it
> hasn't been added! It may prevent bugs but it's not useful.

> **Functional Frankie:** Look a bit closer — instead of `append` the method on
> `Seq` is called `appended`. That means it returns a new Seq with the item
> appended to the end. 
> 
> That's why `account.transaction` stays the same. Nothing can change it. It's
> _immutable._

> **OO Olive:** Doesn't that create horrible performance problems? Creating all
> those new `Seq`s?

> **Functional Frankie:** Not as much as you might think — Scala's immutable
> data types are optimised so that creating modified copies is quick. They do
> this by reusing most of the items in the previous list.

What do you think? Are immutable data types a valuable part of Scala?

## Vals and Vars

> **From OO Olive**
> 
> I've just discovered something that breaks the immutability. Look:

```scala
val account = new Account()

// We save the new Seq to a variable
val newTransactions = account.transactions.appended(new Transaction(-100, LocalDate.now()))

// And then we use `=` to set the transactions
// field on our account to our new Seq!
account.transactions = newTransactions
```

> We could make the `transactions` field private, but then we'd never be able to
> pass them to our `Statement` class or assert on them in the tests. I thought
> about adding a getter like `getTransactions` but I read that these are
> discouraged in Scala. So what's the answer?

> **From Functional Frankie**
> 
> This is because you're using `var` rather than `val`. Look:

```scala
class Account {
  var balance = 0 // <-- here
  var transactions: Seq[Transaction] = Seq()  // <-- and here

  def deposit(amount: Int, date: LocalDate): Unit = {
    balance += amount
    transactions = transactions :+ new Transaction(amount, date)
  }

  def withdraw(amount: Int, date: LocalDate): Unit = {
    balance -= amount
    transactions = transactions :+ new Transaction(-amount, date)
  }
}
```

> That makes those fields mutable — you can change them. If you're going to have
> `var`s then you should absolutely be making them private — no one outside of
> your class should be allowed to make changes. I thought you OO types loved
> encapsulation!
> 
> Even better would be to have them as `val` instead of `var`. Then you can't
> change them at all.

> **OO Olive:** Well we've spoken about how you don't think there should be a
> `balance` variable (see [Derived State](#derived-state)), but how can this
> class be useful if you can never add or remove transactions?

> **FP Olive:** Well, before we look at my solution to this problem, here's an
> example of how you can do it based on your approach. 
> 
> You can make `Account` immutable too.

```scala
class Account(val balance: Int = 0, val transactions: Seq[Transaction] = Seq()) {
  def deposit(amount: Int, date: LocalDate): Account = {
    new Account(
      balance + amount,
      transactions :+ new Transaction(amount, date)
    )
  }

  def withdraw(amount: Int, date: LocalDate): Account = {
    new Account(
      balance - amount,
      transactions :+ new Transaction(-amount, date)
    )
  }
}
```

> And then test it like this:

```scala
"deposit and withdraw and maintain correct balance" in {
  val account = new Account()
    .deposit(1000, Day1)
    .withdraw(499, Day2)
  account.balance shouldEqual 501
}
```

> In this code, instead of changing the state within `Account`, you're returning
> a new `Account` with a new balance and a new collection of `Transaction`s.
> 
> This offers you some really cool stuff! You can save the state of each
> `Account` even as you call methods on it. Like this:

```scala
val accountToday = new Account()
  .deposit(1000, Day1)
val accountTomorrow = accountToday
  .withdraw(499, Day2)
accountToday.balance shouldEqual 1000
accountTomorrow.balance shouldEqual 501
```

> It's really hard to lose track of changes to state here. Because your changes
> don't affect any other `Account` in the program, the potential for bugs is
> way lower.

> **OO Olive:** Okay, I can see how that would be a nice feature, but it's not
> really an `Account` now is it?

> **Functional Frankie:** True — it's more of a `TransactionLog`, or `Ledger`.
