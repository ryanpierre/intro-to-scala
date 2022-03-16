package mixedbank

import java.time.LocalDate

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
