import java.util.{Currency, Locale}

val myList = List(1, 1, 2, 5, 3, 8)
def findLast(givenList: List[Int]): Int = {
  givenList.last
}
findLast(myList)

def findSecondToLastElement(givenList: List[Int]): Int = {
//  val listLength: Int = givenList.length
//  givenList.apply(listLength-2)
  givenList.apply(givenList.length-2)
}
findSecondToLastElement(myList)

def findNthElement(givenList: List[Int], nthElement: Int): Int = {
  givenList.apply(nthElement)
}
findNthElement(myList, 3)


val formatter = java.text.NumberFormat.getCurrencyInstance
val gb = Currency.getInstance(new Locale("gb", "GB"))
val total = 11.85
formatter.setCurrency(gb)
formatter.format(total)