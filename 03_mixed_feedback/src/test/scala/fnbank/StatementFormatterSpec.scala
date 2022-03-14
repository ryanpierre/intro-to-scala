package fnbank

import oobank.Transaction
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate

class StatementFormatterSpec extends AnyWordSpec with Matchers {
  val Day1: LocalDate = LocalDate.EPOCH
  val Day2: LocalDate = Day1.plusDays(1)
  val Day3: LocalDate = Day2.plusDays(1)

  "A Statement Formatter" when {
    "given zero transactions" should {
      "return an empty statement" in {
        StatementFormatter.format(List()) shouldEqual "Amount,Date,Balance\n"
      }
    }

    "given multiple transactions" should {
      "return statement with multiple transactions in reverse chronological order" in {
        StatementFormatter.format(Seq(
          new Transaction(1000, Day1),
          new Transaction(60, Day3),
          new Transaction(-50, Day2)
        )) shouldEqual (
          "Amount,Date,Balance\n" +
          "60,1970-01-03,1010\n" +
          "-50,1970-01-02,950\n" +
          "1000,1970-01-01,1000\n"
        )
      }
    }
  }
}
