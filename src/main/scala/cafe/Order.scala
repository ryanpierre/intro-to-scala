package cafe

import scala.collection.mutable.ArrayBuffer


class Order(val cafe: CafeDetails, var orderedItems: ArrayBuffer[OrderItem] = new ArrayBuffer[OrderItem]()) {
  //returns order object:
  // "item name"  "qty"  "total item price"
  def findItemPrice(orderItem: OrderItem): Double = {
    // look up corresponding menu item for OrderItem name (key)
    // return the price/value
    println(cafe.menu(orderItem.name))
      return cafe.menu(orderItem.name)
  }
}
