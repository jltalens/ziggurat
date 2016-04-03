package ziggurat

import java.util.Date
case class Commit(id: String, contributor: String = "", date: Date = new Date, files: Iterator[String]) {
}
