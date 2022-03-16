package oobank

import java.time.LocalDate
import scala.collection.mutable.ArrayBuffer

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
