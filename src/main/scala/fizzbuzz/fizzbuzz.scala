import scala.collection.mutable.ArrayBuffer

object FizzBuzz {
  def generate(number: Int): String = {
    var i = 0
    val numArray = ArrayBuffer[String]()
    while (i < number) {
      i += 1
      if (i%3 == 0 && i%5==0) {
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

  //  def modThree(number: Int): Boolean = {
  //
  //  }
  def numberToArray(number: Int): ArrayBuffer[Int] = {
    val numArray = ArrayBuffer[Int]()
    var i = 0
    while (i < number) {
      i += 1
      numArray.append(i)
    }
    numArray
  }

}
