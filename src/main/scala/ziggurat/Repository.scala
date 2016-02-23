package ziggurat

trait Repository {
  def extract = Stream(Commit)
}

object Repository extends Repository
