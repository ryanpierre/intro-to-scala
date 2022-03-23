package cafe


import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {

  /**
   * This method should return a multiline string
   * representing a cafe.ReceiptPrinter receipt that should show
   * - shop name, address, phone number
   * - the date and time the receipt was created
   * - each item in the order, with the price. eg:
   * 2 x Blueberry Muffin       8.10
   * 1 x Cappuccino             3.85
   * - the total price
   * - the VAT (20% of total price)
   */
  def receipt(): String = {
    cafe.shopName + "\n" +
      cafe.address + "\n" +
      cafe.phone + "\n" +
      s"Date and Time: ${currentTime}" + "\n" +
      "1 x Cappuccino 3.85"
  }

  var returnValue: String = receipt()
  println(returnValue)
  println(currentTime)


  private
  def currentTime: String = {
    val t = LocalDateTime.now
    val f = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a")
    f.format(t)
  }



}