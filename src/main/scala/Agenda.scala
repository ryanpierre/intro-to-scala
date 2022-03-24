class Agenda (val meetings: List[Meeting]) {

//  def getDay(day: String): List[Any] = {
//    val meetingsForTheDay = meetings.filter(_.day == day)
//    meetingsForTheDay
//  }

//  def getAMMeetings(): List[Any] = {
//    val amMeetings = List[Any].map( meeting => meeting.time.contains("am"))
//    amMeetings
//  }
  def printDaySchedule(day: String): Unit = {
    val meetingsForTheDay = meetings.filter(_.day == day)
    println(s"$day:")
    if (meetingsForTheDay.isEmpty) {
      println(s"  There are no meetings on ${day}")
    }
    for (meeting <- meetingsForTheDay) {
      if (meeting.time.contains("am")) {
        println(s"  ${meeting.day} morning:")
        println(s"  ${meeting.time}: ${meeting.name} \n")
      } else {
        println(s"  ${meeting.day} afternoon:")
        println(s"  ${meeting.time}: ${meeting.name}")
      }
    }
  }

}

class Meeting (val name: String, val day: String, val time: String)

object Main extends App {
  val m1 = new Meeting("Retro", "Friday", "5pm")
  val m2 = new Meeting("Yoga", "Tuesday", "10am")
  val m3 = new Meeting("Team Meeting", "Tuesday", "3pm")
  val agenda = new Agenda(List(m1, m2, m3))
  agenda.printDaySchedule("Monday")
  agenda.printDaySchedule("Tuesday")
}