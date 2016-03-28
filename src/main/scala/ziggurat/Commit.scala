package ziggurat

import java.util.Date
case class Commit(id: String, contributor: String = "", date: Date = new Date) {
  val files = List("A")
}
