package cafe

class OrderItem(val name: String, val quantity: Int, var priceEach: Double = 0.00) extends OrderItemBase{
  // I am expecting the item to automatically go into the order array
}

