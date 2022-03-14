package oobank

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate

class TransactionSpec extends AnyWordSpec with Matchers {
  "A Transaction" should {
    "have a date and an amount" in {
      var transaction = new Transaction(1000, LocalDate.EPOCH)
      transaction.amount shouldEqual 1000
      transaction.date shouldEqual LocalDate.EPOCH
    }
  }
}
