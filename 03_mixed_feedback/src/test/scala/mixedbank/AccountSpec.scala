package mixedbank

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate

class AccountSpec extends AnyWordSpec with Matchers {
  val Day1: LocalDate = LocalDate.EPOCH
  val Day2: LocalDate = Day1.plusDays(1)

  "A Bank Account" should {
    "deposit once and retrieve a balance" in {
      val account = new Account()
        .deposit(1000, Day1)
      account.balance shouldEqual 1000
    }

    "deposit multiple times and retrieve a balance" in {
      val account = new Account()
        .deposit(1000, Day1)
        .deposit(2000, Day2)
      account.balance shouldEqual 3000
    }

    "deposit and withdraw and maintain correct balance" in {
      val account = new Account()
        .deposit(1000, Day1)
        .withdraw(499, Day2)
      account.balance shouldEqual 501
    }

    "deposit and withdraw and maintain correct balance2" in {
      val accountToday = new Account()
        .deposit(1000, Day1)
      val accountTomorrow = accountToday
        .withdraw(499, Day2)
      accountToday.balance shouldEqual 1000
      accountTomorrow.balance shouldEqual 501
    }

    "deposit and withdraw and log transactions" in {
      val account = new Account()
        .deposit(1000, Day1)
        .withdraw(499, Day2)
      account.transactions(0).amount shouldEqual 1000
      account.transactions(0).date shouldEqual Day1
      account.transactions(1).amount shouldEqual -499
      account.transactions(1).date shouldEqual Day2
    }
  }
}
