import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable.ArrayBuffer

class OrderSpec extends AnyWordSpec with Matchers with MockFactory{
  "An order" should {
    "format an order" which {
      "contains the name of the item" in {
        val item1 = mock[ItemBase]
        val mockItems = ArrayBuffer(item1)

        (() => item1.name)
          .stubs()
          .returning("Cappuccino")

        (() => item1.quantity)
          .stubs()
          .returning(1)

        val cccOrder = new Order(mockItems)
        val myItem = cccOrder.items(0)
        myItem.name should include("Cappuccino")
        println(myItem.name)
        //TODO change lines 23-24 so that the order object is iterated

      }
    }
  }

  //      Map(
  //        1 -> "Cafe Latte" -> 4.75,
  //        1 ->  "Flat White" -> 4.75
  //      )
}