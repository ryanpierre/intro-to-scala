package cafe

import scala.collection.mutable.ArrayBuffer


class Order(val cafe: CafeDetails, var orderedItems: ArrayBuffer[OrderItem] = new ArrayBuffer[OrderItem]()) {
  //returns order object:
  // "item name"  "qty"  "total item price"
  def findItemPrice(orderItem: OrderItem): Double = {
      return cafe.menu(orderItem.name)
  }

  def assignItemPrices: Unit ={
    for (orderedItem <- orderedItems){
      orderedItem.priceEach = findItemPrice(orderedItem)
    }
  }

  def totalItemPrice: Unit = {
    for (orderedItem <- orderedItems){
      orderedItem.totalItemPrice = (orderedItem.priceEach * orderedItem.quantity)
    }
  }
}
