package ziggurat

trait Repository {
  def extract = Stream(Commit, Commit)
}

object Repository extends Repository
