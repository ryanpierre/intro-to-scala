package oobank

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate
import scala.collection.mutable.ArrayBuffer

class StatementSpec extends AnyWordSpec with Matchers {
  val Day1: LocalDate = LocalDate.EPOCH
  val Day2: LocalDate = Day1.plusDays(1)
  val Day3: LocalDate = Day2.plusDays(1)

  "A Statement Formatter" when {
    "given zero transactions" should {
      "return an empty statement" in {
        val statement = new Statement(ArrayBuffer())
        statement.format() shouldEqual "Amount,Date,Balance\n"
      }
    }

    "given multiple transactions" should {
      "return statement with multiple transactions in reverse chronological order" in {
        val statement = new Statement(ArrayBuffer(
          new Transaction(1000, Day1),
          new Transaction(60, Day3),
          new Transaction(-50, Day2)
        ))
        statement.format() shouldEqual (
          "Amount,Date,Balance\n" +
            "60,1970-01-03,1010\n" +
            "-50,1970-01-02,950\n" +
            "1000,1970-01-01,1000\n"
          )
      }
    }
  }
}
