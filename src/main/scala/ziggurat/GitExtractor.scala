package ziggurat

trait GitExtractor extends GitDirectory {
  def extract(path: String): Option[Commits] = {
    super.fromString(path) match {
      case None => None
      case _ => Some(Commits)
    }
  }
}

object GitExtractor extends GitExtractor {

}
