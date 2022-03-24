package fnbank

import oobank.Transaction

object StatementFormatter {
  val StatementHeader: String = formatLine("Amount", "Date", "Balance")

  def format: Seq[Transaction] => String =
    sortTransactions _ andThen
      formatTransactions(0) andThen
      addHeader

  private def sortTransactions(txns: Seq[Transaction]) =
    txns.sortBy(tx => tx.date)

  private def formatTransactions: Int => Seq[Transaction] => String = (startingBalance: Int) => {
    case Nil => ""
    case tx :: txs =>
      formatTransactions(startingBalance + tx.amount)(txs)
        .concat(formatLine(tx.amount, tx.date, startingBalance + tx.amount))
  }

  private def addHeader(formattedTxns: String): String = StatementHeader.concat(formattedTxns)

  private def formatLine(a: Any, b: Any, c: Any) = s"$a,$b,$c\n"
}
