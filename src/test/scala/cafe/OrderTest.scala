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
    "format an order" which {
      "contains at least one item" in {
        var myOrder = new Order(coffeeConnectionCafe)
//        val mockItems = ArrayBuffer(item1)
        myOrder.orderedItems.append(new OrderItem("Cappuccino", 1))
        println(myOrder.orderedItems)


          myOrder.findItemPrice(myOrder.orderedItems(0)) should be(3.85)
//        println(myItem.name)
        //TODO change lines 23-24 so that the order object is iterated
        //for each item in items
          //test that the name is right
          //test that the quantity is right
          //test that the price is right
      }
    }
  }

  //      Map(
  //        1 -> "Cafe Latte" -> 4.75,
  //        1 ->  "Flat White" -> 4.75
  //      )
}