package ziggurat

trait GitExtractor extends GitDirectory {
  def extract(path: String): Option[Commits] = {
    super.fromString(path)
  }
}

object GitExtractor extends GitExtractor {

}
