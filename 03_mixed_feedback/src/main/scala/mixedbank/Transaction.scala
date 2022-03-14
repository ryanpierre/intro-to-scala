package mixedbank

import java.time.LocalDate

class Transaction(_amount: Int, _date: LocalDate) {
  var amount = _amount
  var date = _date
}
