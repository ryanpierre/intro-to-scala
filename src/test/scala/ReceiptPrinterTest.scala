import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class ReceiptPrinterSpec extends AnyWordSpec with Matchers with MockFactory {
  val coffeeConnectionCafe = new CafeDetails(
    "The Coffee Connection",
    "123 Lakeside Way",
    "16503600708",
    Map(
      "Cafe Latte" -> 4.75,
      "Flat White" -> 4.75,
      "Cappuccino" -> 3.85,
      "Single Espresso" -> 2.05,
      "Double Espresso" -> 3.75,
      "Americano" -> 3.75,
      "Cortado" -> 4.55,
      "Tea" -> 3.65,
      "Choc Mudcake" -> 6.40,
      "Choc Mousse" -> 8.20,
      "Affogato" -> 14.80,
      "Tiramisu" -> 11.40,
      "Blueberry Muffin" -> 4.05,
      "Chocolate Chip Muffin" -> 4.05,
      "Muffin Of The Day" -> 4.55
    )
  )

  val printer = new ReceiptPrinter(
    coffeeConnectionCafe
  )
  "A ReceiptPrinter" should {
    "format a receipt" which {
      "contains the name of the cafe" in {
        printer.receipt should include("The Coffee Connection")
      }
      "contains the address of the cafe" in {
        printer.receipt should include("123 Lakeside Way")
      }
      "contains the phone of the cafe" in {
        printer.receipt should include("16503600708")
      }
      "contains the date and time the receipt was created" in {
        printer.receipt should include("Date and Time: ")
      }
      "contains each item in the order, with the price. eg:" +
        "2 x Blueberry Muffin       8.10" +
        "1 x Cappuccino             3.85" in {
        printer.receipt should include("1 x Cappuccino 3.85")
      }
    }
  }
}
