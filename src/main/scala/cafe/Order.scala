package cafe

import scala.collection.mutable.ArrayBuffer


class Order(val cafe: CafeDetails, var orderedItems: ArrayBuffer[OrderItem] = new ArrayBuffer[OrderItem](), var orderTotal: Double = 0.00) {
  def processOrder: Unit = {
    totalItemPrice
    processOrderTotal
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

  def processOrderTotal: Double = {
    for (orderedItem <- orderedItems) {
      orderTotal = orderedItem.totalItemPrice + orderTotal
    }
    return orderTotal
  }
}
