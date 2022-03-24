# Receipt Printer

In this step you will expand your knowledge of scala and start using classes.

## A scala programme with classes

Create a new folder with this structure:
```
agenda/
├── build.sbt   <- you can leave this empty for now
└── src/
    └── main/
        └── scala/
            └── Agenda.scala
```

Inside `Agenda.scala`, paste this code:
```scala
class Agenda (val meetings: List[Meeting]) {

  def printDaySchedule(day: String): Unit = {
    val meetingsForTheDay = meetings.filter(_.day == day)
    println(s"$day:")
    for (meeting <- meetingsForTheDay) println(s"  ${meeting.time}: ${meeting.name}")
  }

}

class Meeting (val name: String, val day: String, val time: String)

object Main extends App {
  val m1 = new Meeting("Retro", "Friday", "5pm")
  val m2 = new Meeting("Yoga", "Tuesday", "10am")
  val m3 = new Meeting("Team Meeting", "Tuesday", "3pm")
  val agenda = new Agenda(List(m1, m2, m3))
  agenda.printDaySchedule("Monday")
  agenda.printDaySchedule("Tuesday")
}
```

You can run this programme using `sbt run`.

There are a few things happening here:

* We are defining the entry point of the project differently from last time. Here, instead of a function called `main`, we use an object called `Main` that inherits from the class `App`. You can use this anytime you want your programme to do something when it's run.

* We define two classes, named `Agenda` and `Meeting`. As you can see, they don't have an initializer or constructor method. In Scala, the primary constructor of a class is implicit - it is defined directly through the class signature. So the line

```scala
class Meeting (val name: String, val day: String, val time: String)
```
defines a class which takes three string arguments at initialisation. These arguments become instance variables of the object created. In this case, because we are using `val`, they are public and immutable. That is why we can access them directly in the `Agenda` class.

Check the different ways these variables can be defined [here](https://docs.scala-lang.org/cheatsheets/index.html#object_orientation).


* We define a method `printDaySchedule` that takes a day of the week as a string, and prints out all the meetings that happen on that day.

* We use the placeholder syntax (`_`) to define a function in our filter: `meetings.filter(_.day == day)`. This is syntactic sugar for writing functions in Scala, and is equivalent to writing `meetings.filter( meeting => meeting.day == day)`.


### Add some features

Play with this code. Do you understand what each line does?
* Modify the `printDaySchedule` method so that it displays a message when there are no events on a given day (eg: "There are no meeting on Friday").

* Modify `printDaySchedule` so that am and pm events are displayed separately. Something like:
```
Tuesday morning:
  10am: Yoga

Tuesday afternoon:
  3pm: Team Meeting
```

Can you improve this code to make it more functional?
* Composing simple functions together
* Using immutable variables
* Using pure functions
* Using map/reduce/filter/recursion rather than for/while


## Receipt Printer

For this challenge you'll have to:
[ ] Create a new Scala project, adding a testing library as a dependency
[ ] Create a cafe.ReceiptPrinter class (see skeleton code below)
[ ] Use TDD to write the code of the receipt method
[ ] Create private methods to extract some logic from the receipt method

**stretch**
Use TDD to create a new class called Till which takes a CafeDetail instance at initialisation.
It should have methods that
[ ] Show the menu
[ ] Allow the user to add an item to their order or throw an error if what they enter is not on the menu
[ ] Finalise the order and print the statement (by calling on the receipt printer)

Use the resources provided and your own research to get to a working solution.

Here are some skeleton files:
```scala
// src/main/scala/cafe.ReceiptPrinter.scala
class CafeDetails (
  val shopName: String,
  val address: String,
  val phone: String,
  val prices: Map[String, Double]
)

class cafe.ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {

  /**
   * This method should return a multiline string
   * representing a cafe.ReceiptPrinter receipt that should show
   * - shop name, address, phone number
   * - the date and time the receipt was created
   * - each item in the order, with the price. eg:
   *     2 x Blueberry Muffin       8.10
   *     1 x Cappuccino             3.85
   * - the total price
   * - the VAT (20% of total price)
   */
  def receipt: String = {
    cafe.shopName
  }
}
```

```scala
// src/test/scala/cafe.ReceiptPrinterTest.scala
import cafe.ReceiptPrinter
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class cafe.ReceiptPrinterSpec extends AnyWordSpec with Matchers {
  val coffeeConnectionCafe = new CafeDetails(
    "The Coffee Connection",
    "123 Lakeside Way",
    "16503600708",
    Map(
      "Cafe Latte" -> 4.75,
      "Flat White" -> 4.75,
      "Cappuccino" -> 3.85,
      "Single Espresso" -> 2.05,
      "Double Espresso" -> 3.75,
      "Americano" -> 3.75,
      "Cortado" -> 4.55,
      "Tea" -> 3.65,
      "Choc Mudcake" -> 6.40,
      "Choc Mousse" -> 8.20,
      "Affogato" -> 14.80,
      "Tiramisu" -> 11.40,
      "Blueberry Muffin" -> 4.05,
      "Chocolate Chip Muffin" -> 4.05,
      "Muffin Of The Day" -> 4.55
    )
  )

  "A cafe.ReceiptPrinter" should {
    "format a receipt" which {
      "contains the name of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)
        )
        printer.receipt should include ("The Coffee Connection")
      }
      // add more tests here.
    }
  }
}
```

**Note:** You'll notice the style of testing is slightly different in these files. In the last step we have used the `funsuite` style, but as a developer, you'll have to adapt to the style that you team is using. Here we chose the`AnyWordSpec` style, which might be closer to what you'll use on the job.

## Resources:

* [A build.sbt reference file](https://github.com/scala/hello-world.g8/blob/main/src/main/g8/build.sbt)
* [Scala classes](https://docs.scala-lang.org/tour/classes.html)
* [Overview of testing styles in scalatest](https://www.scalatest.org/user_guide/selecting_a_style).
* [Using should matchers](https://www.scalatest.org/user_guide/using_matchers)
* [Using the Map data structure](https://docs.scala-lang.org/overviews/collections/maps.html#operations-in-class-map) (this is a structure similar to ruby hashes)
* [Using mocks in ScalaTest](https://www.scalatest.org/user_guide/testing_with_mock_objects)
