import scala.collection.mutable.ArrayBuffer

object FizzBuzz {

  //  var numArray = ???

  def generate(number: Int): String = {
    //evaluateNumber(numberToArray(number)).mkString(", ")
    var i = 0
    val numArray = ArrayBuffer[String]()
    while (i < number) {
      i += 1

      if (i % 3 == 0 && i % 5 == 0) {
        numArray.append("FizzBuzz")
      } else if (i % 3 == 0) {
        numArray.append("Fizz")
      } else if (i % 5 == 0) {
        numArray.append("Buzz")
      } else
        numArray.append(s"$i")
    }
    numArray.mkString(", ")
  }
}

//  def numberToArray(number: Int): ArrayBuffer[Int] = {
//    val numArray = ArrayBuffer[Int]()
//    var i = 0
//    while (i < number) {
//      i += 1
//      numArray.append(i)
//    }
//    println(numArray)
//    numArray
//  }
//
//  def evaluateNumber(myIntArray: ArrayBuffer[Int]): ArrayBuffer[Any] = {
//    var myStringArray = ArrayBuffer[Any]()
//    myStringArray = myIntArray.map(element => if (element % 3 == 0 && element % 5 == 0) {
//      myStringArray :+ ("FizzBuzz")
//    } else if (element % 3 == 0) {
//      myStringArray :+ ("Fizz")
//    } else if (element % 5 == 0) {
//      myStringArray :+ ("Buzz")
//    } else
//      myStringArray :+ (s"$element")
//    )
//    println(myStringArray)
//    myStringArray
//  }

