package oobank

import scala.collection.mutable.ArrayBuffer

class Statement(transactions: ArrayBuffer[Transaction]) {
  val StatementHeader: String = "Amount,Date,Balance"

  def format(): String = {
    var lines: ArrayBuffer[String] = ArrayBuffer()
    var balance = 0

    val sortedTransactions = transactions.sortBy(t => t.date)

    sortedTransactions.foreach(transaction => {
      balance += transaction.amount
      lines.append(s"${transaction.amount},${transaction.date},$balance")
    })

    lines.append(StatementHeader)
    lines.reverse.mkString("\n") + "\n"
  }
}
