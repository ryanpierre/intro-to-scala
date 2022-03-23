package cafe


import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReceiptPrinter(val cafe: CafeDetails, var order: Order) {

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
    printOrderItems
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

  def printOrderItems: String = {
    val stringBuilder = new StringBuilder()
    for (orderItem <- order.orderedItems) {
      stringBuilder.append(f"${orderItem.quantity} x ${orderItem.name}%-21s${orderItem.totalItemPrice}%1.2f \n")
    }
    return stringBuilder.toString()
  }


}