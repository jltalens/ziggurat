package ziggurat

trait Extractor extends Directory {
  def extract(path: String): Option[Commit] = {
    super.fromString(path) match {
      case None => None
      case _ => Some(Commit)
    }
  }
}

object Extractor extends Extractor {

}
