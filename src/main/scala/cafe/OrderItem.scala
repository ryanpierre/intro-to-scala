package cafe

class OrderItem(val name: String, val quantity: Int, var priceEach: Double = 0.00, var totalItemPrice: Double = 0.00) extends OrderItemBase{
}

