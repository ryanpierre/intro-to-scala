package cafe

import scala.collection.mutable.ArrayBuffer


class Order(val cafe: CafeDetails, var orderedItems: ArrayBuffer[OrderItem] = new ArrayBuffer[OrderItem](), var itemsTotal: Double = 0.00, var totalVAT: Double = 0.00, var orderTotal: Double = 0.00) {
  def processOrder: Unit = {
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

  def processItemsTotal: Double = {
    totalItemPrice
    for (orderedItem <- orderedItems) {
      itemsTotal = orderedItem.totalItemPrice + itemsTotal
    }
    return itemsTotal
  }

  def processVATTotal: Double = {
    processItemsTotal
    totalVAT = itemsTotal * 0.2
    return totalVAT
  }

  def processOrderTotal: Double = {
    processVATTotal
    orderTotal = itemsTotal + totalVAT
    return orderTotal
  }
}
