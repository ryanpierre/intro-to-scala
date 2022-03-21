
class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {

  /**
   * This method should return a multiline string
   * representing a ReceiptPrinter receipt that should show
   * - shop name, address, phone number
   * - the date and time the receipt was created
   * - each item in the order, with the price. eg:
   *     2 x Blueberry Muffin       8.10
   *     1 x Cappuccino             3.85
   * - the total price
   * - the VAT (20% of total price)
   */
  def receipt: String = {
    cafe.shopName +
    cafe.address +
    cafe.phone
  }
}
