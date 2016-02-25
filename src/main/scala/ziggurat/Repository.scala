package ziggurat
import java.io.File
import java.text.SimpleDateFormat

import scala.util.matching.Regex

abstract class Repository (dir: File) {
  def extract: Iterator[Commit]
}

object Repository {
  def apply(dir: File): Repository =
    new RepositoryImp(dir)
}

class RepositoryImp(dir: File) extends Repository(dir) {

  final val gitProcess: GitProcess = new GitProcess(dir)
  final val RCommitFormat: Regex = """(.*)#(.*)#(.*)""".r
  final val dateParser: SimpleDateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z")

  override def extract: Iterator[Commit] = {
    gitProcess.commits.collect { case RCommitFormat(id, contributor, date) => Commit(id, contributor, dateParser.parse(date)) }
  }

}
