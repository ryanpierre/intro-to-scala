import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ReceiptPrinterSpec extends AnyWordSpec with Matchers {
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

      "A ReceiptPrinter" should {
        "format a receipt" which {
          "contains the name of the cafe" in {
            val printer = new ReceiptPrinter(
              coffeeConnectionCafe,
              Map("Cafe Latte" -> 1)
            )
            printer.receipt should include ("The Coffee Connection")
          }
          // add more tests here.
          "contains the address of the cafe" in {
            val printer = new ReceiptPrinter(
              coffeeConnectionCafe,
              Map("Cafe Latte" -> 1)
            )
            printer.receipt should include ("123 Lakeside Way")
          }
        }
      }
}
