package cafe

import scala.collection.mutable.ArrayBuffer


class Order(val cafe: CafeDetails, var orderedItems: ArrayBuffer[OrderItem] = new ArrayBuffer[OrderItem]()) {
  def processOrder: Unit = {
    totalItemPrice
  }

  private

  def findItemPrice(orderItem: OrderItem): Double = {
      return cafe.menu(orderItem.name)
  }

  def assignItemPrices: Unit ={
    for (orderedItem <- orderedItems){
      orderedItem.priceEach = findItemPrice(orderedItem)
    }
  }

  def totalItemPrice: Unit = {
    assignItemPrices
    for (orderedItem <- orderedItems){
      orderedItem.totalItemPrice = (orderedItem.priceEach * orderedItem.quantity)
    }
  }
}
