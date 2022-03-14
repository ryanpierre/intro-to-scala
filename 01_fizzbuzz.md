# Fizzbuzz in Scala

In this exercise you will test-drive Fizzbuzz. As with all Makers materials you
will be expected to learn by doing, by thinking hard, and through independent
research. You will be rewarded for your efforts with learning that you can
really apply to solve the challenges ahead. Consider this a hiking trail, not a
tour bus.

Let's get started.

## Installing the prerequisites

Go ahead and install these prerequisites if you haven't already.

* Install the [IntelliJ IDE](https://www.jetbrains.com/edu-products/download/#section=idea-Scala).
* [Install Scala](https://docs.scala-lang.org/getting-started/index.html#install-scala-on-your-computer).
  * This should install a JVM if you don't have one already.
  * You may have to restart your terminal for everything to work after this is done.

## Hello World

Let's begin with a Hello World.

Create a new directory for your project, and cd into it:
```
mkdir hello-world
cd hello-world
```

Now create a file for your code. In Scala, the directory structure is quite specific:
```
mkdir -p src/main/scala/
touch src/main/scala/Hello.scala
```

`Hello.scala` is the file where you'll write your code.

Open this file, and type in:

```scala
object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello World")
  }
}
```

In order to run your code, you'll have to create another file, called `build.sbt`.
```
touch build.sbt
```
This file can stay empty for now. It's just telling `sbt` that this is a project that can be compiled. Later this file may contain your version of Scala and some information about the project, similarly to what a Gemfile or package.json file would contain.


This is the final directory structure that you should have:
```
hello-world/
├── build.sbt
└── src/
    └── main/
        └── scala/
            └── Hello.scala
```

You can now (build and) run your code by typing `sbt run`.
In IntelliJ, you could also run this code by clicking on the green ▶️ button next to the function.


Got it saying "Hello World"? Great!

There are a few things happening here:
* We define an **object**. In Scala, all code should live either inside a class or inside an object. Here we don't need a class, so an object it is!

* We define **a function called `main`**. This function is the entry point of your programme. In other words, this is what will be run when you execute the programme. 

A main function typically looks like this:
```scala
  def main(args: Array[String]): Unit = {
    // some code
  }
```

* We use `sbt` or Simple Build Tool to compile the code and run the programme. We'll use this tool later on to run tests, create new projects and manage dependencies. Think of it as the `npm` of Scala.


## Fizzbuzz

Your task is to test-drive a program that takes a max number and returns a
string. The string should list out the numbers counting up to and including the
given max number, substituting Fizz where the number is divisible by 3, Buzz
where it is divisible by 5, and FizzBuzz where it is divisible by both 3 and 5.

Here's an example:

```
CALLING:
  FizzBuzz.generate(15)
SHOULD RETURN:
  "1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz"
```

You're going to test drive this, which will be much easier if you avoid printing
to the terminal.

Here is a starting point in Scala

```scala
// build.sbt
scalaVersion := "2.13.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test

// src/main/scala/FizzBuzz.scala
object FizzBuzz {
  def generate(number: Int): String = {
    "1"
  }
}

// src/test/scala/FizzBuzzTest.scala
import org.scalatest.funsuite.AnyFunSuite
class FizzBuzzTest extends AnyFunSuite {
  test("lists the numbers up to one") {
    assert(FizzBuzz.generate(1) === "1")
  }

  test("lists the numbers up to two") {
    assert(FizzBuzz.generate(2) === "1, 2")
  }
}
```

Run the tests using `sbt test`.

Make sure to run the tests first, then write new tests to drive your code.
Use this opportunity to observe how different errors show up in Scala.

You're done when your code passes the following acceptance criteria:

```
CALLING:
  FizzBuzz.generate(100)
SHOULD RETURN:
  "1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, 17, Fizz, 19, Buzz, Fizz, 22, 23, Fizz, Buzz, 26, Fizz, 28, 29, FizzBuzz, 31, 32, Fizz, 34, Buzz, Fizz, 37, 38, Fizz, Buzz, 41, Fizz, 43, 44, FizzBuzz, 46, 47, Fizz, 49, Buzz, Fizz, 52, 53, Fizz, Buzz, 56, Fizz, 58, 59, FizzBuzz, 61, 62, Fizz, 64, Buzz, Fizz, 67, 68, Fizz, Buzz, 71, Fizz, 73, 74, FizzBuzz, 76, 77, Fizz, 79, Buzz, Fizz, 82, 83, Fizz, Buzz, 86, Fizz, 88, 89, FizzBuzz, 91, 92, Fizz, 94, Buzz, Fizz, 97, 98, Fizz, Buzz"
```

### What you'll need to learn

Amongst other things, you'll need to learn:

* How to [use a project that has tests](https://docs.scala-lang.org/getting-started/sbt-track/testing-scala-with-sbt-on-the-command-line.html)
* [Scala Methods](https://docs.scala-lang.org/overviews/scala-book/methods-first-look.html)
* [If/else in Scala](https://docs.scala-lang.org/overviews/scala-book/if-then-else-construct.html)
* [String manipulation](https://alvinalexander.com/scala/scala-string-examples-collection-cheat-sheet/)
* [Some more Scala syntax](https://docs.scala-lang.org/cheatsheets/index.html)

How "functional" is your code? Check it against this checklist:

* Does is make use of simple functions composed together? (more functional)
* Does it use for or while loops (less functional, try map, reduce or recursion instead)
* Does it have pure functions (more functional)
* Are you mutating variables? (less functional)

Try to refactor it to make it more functional.