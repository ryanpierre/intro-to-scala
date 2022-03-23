package cafe

import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec



class OrderTest extends AnyWordSpec with Matchers with MockFactory{

  var coffeeConnectionCafe = new CafeDetails(
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

  "An order" should {
    var myOrder = new Order(coffeeConnectionCafe)
    myOrder.orderedItems.append(new OrderItem("Cappuccino", 1))
    myOrder.orderedItems.append(new OrderItem("Blueberry Muffin", 2))
    myOrder.processOrder
    "format an order" which {
      "contains the correct individual price for 1 item" in {
        myOrder.orderedItems(0).priceEach should be(3.85)
      }
      "contains the correct price of items that have a quantity > 1" in {
        myOrder.orderedItems(1).totalItemPrice should equal(8.10)
      }
      "totals the price of all items" in {
        myOrder.orderTotal should equal(11.95)
      }
    }
  }

}